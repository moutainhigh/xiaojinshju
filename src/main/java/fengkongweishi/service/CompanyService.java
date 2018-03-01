package fengkongweishi.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayTradePagePayRequest;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.company.CompanyRepository;
import fengkongweishi.entity.company.TeamForSearch;
import fengkongweishi.entity.companyremainderlog.CompanyRemainderLog;
import fengkongweishi.entity.companyremainderlog.CompanyRemainderLogRepository;
import fengkongweishi.entity.companyverification.CompanyVerification;
import fengkongweishi.entity.companyverification.CompanyVerificationRepository;
import fengkongweishi.entity.customer.CustomerRepository;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.role.Role;
import fengkongweishi.entity.user.User;
import fengkongweishi.entity.user.UserRepository;
import fengkongweishi.enums.*;
import fengkongweishi.util.Common;
import fengkongweishi.util.FailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author huanghengkun
 */
@Service
public class CompanyService {

    @Value("${alipay.url}")
    private String url;
    @Value("${alipay.appId}")
    private String appId;
    @Value("${alipay.appPrivateKey}")
    private String appPrivateKey;
    @Value("${alipay.format}")
    private String format;
    @Value("${alipay.charset}")
    private String charset;
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;
    @Value("${alipay.signType}")
    private String signType;
    @Value("${alipay.returnUrl}")
    private String returnUrl;
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyRemainderLogRepository companyRemainderLogRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyVerificationRepository companyVerificationRepository;
    @Autowired
    ParameterService parameterService;

    @Transactional(rollbackOn = Exception.class)
    public void directDeposit(Company company, Integer amount, PaymentChannelEnum channel, String reason) {
        if (amount == null) {
            throw new FailResponse(9111, "充值金额不能空");
        }
        Company findOne = companyRepository.findOne(company.getId());
        if (findOne == null) {
            throw new FailResponse(5467, "公司不存在");
        }

        findOne.deposit(amount);
        companyRepository.save(findOne);

        CompanyRemainderLog companyRemainderLog = new CompanyRemainderLog();
        companyRemainderLog.setByUser(Common.getPrincipal().getUser());
        companyRemainderLog.setChannel(channel);
        companyRemainderLog.setCompany(findOne);
        companyRemainderLog.setCreateTime(new Date());
        companyRemainderLog.setType(ChargeTypeEnum.DEPOSIT);
        companyRemainderLog.setValue(amount);
        companyRemainderLog.setNote(reason);
        companyRemainderLog.setStatus(ChargeStatusEnum.TRADE_SUCCESS);
        companyRemainderLogRepository.save(companyRemainderLog);
    }


    @Transactional(rollbackOn = Exception.class)
    public Integer indirectDeposit(Company company, Integer amount, PaymentChannelEnum channel, PlatformEnum platform) {
        if (amount == null) {
            throw new FailResponse(9111, "充值金额不能空");
        }
        Company findOne = companyRepository.findOne(company.getId());
        if (findOne == null) {
            throw new FailResponse(5467, "公司不存在");
        }

        CompanyRemainderLog companyRemainderLog = new CompanyRemainderLog();

        if (amount.equals(1000)) {
            companyRemainderLog.setValue(1100);
        } else if (amount.equals(5000)) {
            companyRemainderLog.setValue(5750);
        } else if (amount.equals(10000)) {
            companyRemainderLog.setValue(12000);
        } else {
            companyRemainderLog.setValue(amount);
        }
        if (Common.getPrincipal() != null) {
            companyRemainderLog.setByUser(Common.getPrincipal().getUser());
        }
        companyRemainderLog.setChannel(channel);
        companyRemainderLog.setCompany(findOne);
        companyRemainderLog.setCreateTime(new Date());
        companyRemainderLog.setType(ChargeTypeEnum.DEPOSIT);
        companyRemainderLog.setRealValue(amount);
        companyRemainderLog.setPlatform(platform);
        companyRemainderLog.setStatus(ChargeStatusEnum.WAIT_BUYER_PAY);
        CompanyRemainderLog save = companyRemainderLogRepository.save(companyRemainderLog);
        return save.getId();
    }

    @Transactional(rollbackOn = Exception.class)
    public void consume(Company company, Integer value, PaymentChannelEnum channel, PersonReport report) {

        company.withdraw(value);
        companyRepository.save(company);

        CompanyRemainderLog companyRemainderLog = new CompanyRemainderLog();
        if (Common.getPrincipal() != null) {
            companyRemainderLog.setByUser(Common.getPrincipal().getUser());
        }
        companyRemainderLog.setChannel(channel);
        companyRemainderLog.setCompany(company);
        companyRemainderLog.setCreateTime(new Date());
        companyRemainderLog.setType(ChargeTypeEnum.CONSUME);
        companyRemainderLog.setValue(value);
        companyRemainderLog.setReport(report);
        companyRemainderLog.setStatus(ChargeStatusEnum.TRADE_SUCCESS);
        companyRemainderLogRepository.save(companyRemainderLog);
    }

    @Transactional
    public String taobaoDeposit(Integer amount) {

        Company company = Common.getPrincipal().getCompany();
        // 创建API对应的request
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(url, appId, appPrivateKey, format, charset, alipayPublicKey, signType);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        Integer id = indirectDeposit(company, amount, PaymentChannelEnum.FRONTEND, PlatformEnum.ALIPAY);

        // 在公共参数中设置回跳和通知地址
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        // 填充业务参数
        alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"company_" + id + "\","
                + "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," + "    \"total_amount\":" + amount + ","
                + "    \"subject\":\"deposit\"," + "    \"body\":\"company deposit\""

                + "  }");
        try {
            // 调用SDK生成表单
            return alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        throw new FailResponse(6511, "生成支付宝支付页错误");
    }

    public Page<Company> teamListAndSearch(TeamForSearch searchBean, Pageable pageable,Company company) {
        Page<Company> companyPage = companyRepository.findAll(new Specification<Company>() {
            @Override
            public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                Predicate companyEqual = cb.equal(root.get("parent"), company);
                predicateList.add(companyEqual);
                if (searchBean != null) {
                    if (!StringUtils.isEmpty(searchBean.getTeamName())) {
                        Predicate teamNameEqual = cb.equal(root.get("companyName"), searchBean.getTeamName());
                        predicateList.add(teamNameEqual);
                    }

                    if (!StringUtils.isEmpty(searchBean.getManagerName())) {
                        Predicate managerNameEqual = cb.equal(root.get("manager").get("nickname"), searchBean.getManagerName());
                        predicateList.add(managerNameEqual);
                    }

                    if (!StringUtils.isEmpty(searchBean.getManagerMobile())) {
                        Predicate mobileEqual = cb.equal(root.get("manager").get("username"), searchBean.getManagerMobile());
                        predicateList.add(mobileEqual);
                    }
                }
                query.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        }, pageable);
        return companyPage;
    }

    public void addTeam(Company company, User manager, String companyName) {
        Company team = companyRepository.findByCompanyNameAndParent(companyName,company);
        if(team != null){
            throw new FailResponse(ExceptionEnum.TEAM_ALREADY_EXIST);
        }else{
            team = new Company();
        }
        Date date = new Date();
        team.setCompanyName(companyName);
        team.setManager(manager);
        team.setParent(company);
        team.setStatus(CompanyStatusEnum.ENABLED);
        team.setCreateTime(date);
        team.setVerifyTime(date);
        Set<SystemEditionEnum> openEditions = new HashSet<>();
        Set<SystemEditionEnum> parentEditions = company.getOpenEditions();
        for (SystemEditionEnum systemEditionEnum:parentEditions){
            openEditions.add(systemEditionEnum);
        }
        team.setOpenEditions(openEditions);
        team.setAppCode(parameterService.getMD5(new Date().toString().getBytes(Charset.forName("utf-8"))));
        companyRepository.save(team);
        Role role = new Role();
        role.setId(2);
        role.setName("ROLE_MANAGER");
        manager.setCompany(team);
        manager.setJoinCompanyTime(date);
        manager.setRole(role);
        userRepository.save(manager);
    }

    public void editionupgreade(Company company, User user,String edition) {
        CompanyVerification companyVerification = new CompanyVerification();
        companyVerification.setCompany(company);
        companyVerification.setApplyUser(user);
        companyVerification.setApplyInfo(edition);
        companyVerification.setApplyType(ApplyTypeEnum.EDITIONUPGREADE);
        companyVerification.setApplyTime(new Date());
        companyVerificationRepository.save(companyVerification);
    }
}
