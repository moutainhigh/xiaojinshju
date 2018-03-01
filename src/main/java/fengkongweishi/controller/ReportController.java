package fengkongweishi.controller;

import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.company.CompanyRepository;
import fengkongweishi.entity.customer.Customer;
import fengkongweishi.entity.moxiecallbacklog.MoxieCallbackLogRepository;
import fengkongweishi.entity.personreport.*;
import fengkongweishi.entity.personreport.po.CourtJudgment;
import fengkongweishi.enums.ChargeTypeEnum;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.enums.MoxieTaskStatusEnum;
import fengkongweishi.enums.PaymentChannelEnum;
import fengkongweishi.service.CompanyService;
import fengkongweishi.service.CustomerService;
import fengkongweishi.service.report.*;
import fengkongweishi.util.Common;
import fengkongweishi.util.FailResponse;
import fengkongweishi.util.ResponseBody;
import fengkongweishi.util.ValidUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 个信报告控制器
 *
 * @author huanghengkun
 * @date 2018/01/16
 * @see fengkongweishi.aop.AuthorizationAspect
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    CompanyService companyService;
    @Autowired
    PersonReportRepository personReportRepository;
    @Autowired
    BaseReportService baseReportService;
    @Autowired
    MoxieCallbackLogRepository moxieCallbackLogRepository;
    @Autowired
    AnalyseService analyseService;
    @Autowired
    ViolationReportService violationReportService;
    @Autowired
    MoxieTaobaoReportService moxieTaobaoReportService;
    @Autowired
    MoxieCarrierReportService moxieCarrierReportService;

    Logger logger = LoggerFactory.getLogger(ReportController.class);

    @RequestMapping(value = "/base", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER') or hasRole('EMPLOYEE')")
    @Transactional(rollbackOn = Exception.class)
    public ResponseBody base(@Valid @RequestBody BaseParameterDTO baseParameterDTO, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return ValidUtils.getFirstErrorInfo(result);
        }
        //经过AuthorizationAspect的权限切面,appCode非空且正确
        Company company = companyRepository.findByAppCode(request.getHeader("appCode"));
        if (!company.getOpenEditions().contains(baseParameterDTO.getEdition())) {
            throw new FailResponse(ExceptionEnum.REPORT_COMPANY_HAVE_NOT_OPEN_EDTION);
        }
        PersonReport report = new PersonReport(baseParameterDTO.getName(), baseParameterDTO.getMobile(),
                baseParameterDTO.getIdCard(), baseParameterDTO.getBankCard(), baseParameterDTO.getCommonAddress(),
                baseParameterDTO.getEdition());
        int reportPrice = report.reportPrice();
        report.setCreateByCompany(company);
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser != null) {
            if (!currentUser.getCompany().getId().equals(company.getId())) {
                throw new FailResponse(ExceptionEnum.REPORT_COMPANY_AND_APPCODE_INCONSISTENCIES);
            }
            report.setCreateBy(currentUser.getUser());
        }
        if (company.getRemainder() < reportPrice) {
            throw new FailResponse(ExceptionEnum.REPORT_COMPANY_HAVE_NOT_ENOUGH_REMAINDER);
        }
        companyRepository.save(company);
        baseReportService.analyseReport(report);
        // customer
        Customer customer = customerService.putCustomerAndRecordLog(report, reportPrice, ChargeTypeEnum.CONSUME);
        report.setCustomer(customer);
        personReportRepository.save(report);
        // remaindLog
        PaymentChannelEnum paymentChannel = null;
        try {
            paymentChannel = PaymentChannelEnum.valueOf(baseParameterDTO.getEdition().toString());
        } catch (Exception e) {
            logger.info("paymentChannel转换错误,reportId=" + report.getId() + ",edition=" + baseParameterDTO.getEdition().toString());
            e.printStackTrace();
        }
        companyService.consume(company, reportPrice, paymentChannel, report);
        Map<String, Integer> map = new HashMap<>();
        map.put("reportId", report.getId());
        return new ResponseBody(map);
    }

    /**
     * 判断用户的姓名、身份证和手机号是否一致
     */
    @RequestMapping(value = "/authMobile", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER') or hasRole('EMPLOYEE')")
    public ResponseBody handleAuthMobile(String name, String idCard, String mobile) {
        String idCardRegex = "[0-9]{17}([0-9]|X|x)";
        if (!Pattern.matches(idCardRegex, idCard)) {
            throw new FailResponse(ExceptionEnum.REPORT_AUTHMOBILE_IDCARD_ERROR);
        }
        if (name.length() < 2 || name.length() > 5) {
            throw new FailResponse(ExceptionEnum.REPORT_AUTHMOBILE_NAME_ERROR);
        }
        String mobileRegex = "((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}";
        if (!Pattern.matches(mobileRegex, mobile)) {
            throw new FailResponse(ExceptionEnum.REPORT_AUTHMOBILE_MOBILE_ERROR);
        }
        return new ResponseBody(analyseService.handleAuthMobile(name, idCard, mobile));
    }

    @RequestMapping(value = "/{reportId}/violation", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER') or hasRole('EMPLOYEE')")
    public ResponseBody vehicleLicense(@PathVariable("reportId") Integer reportId, @Valid @RequestBody VehLicenseDTO vehLicenseDTO, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return ValidUtils.getFirstErrorInfo(result);
        }
        //经过AuthorizationAspect的权限切面,appCode非空且正确
        Company company = companyRepository.findByAppCode(request.getHeader("appCode"));
        PersonReport report = personReportRepository.findOne(reportId);
        report.setPlateNumber(vehLicenseDTO.getPlateNumber());
        report.setVin(vehLicenseDTO.getVin());
        report.setEngineNo(vehLicenseDTO.getEngineNo());
        report.setCarType(vehLicenseDTO.getCarType());
        violationReportService.analyseReport(report);
        Map<String, Integer> map = new HashMap<>();
        map.put("reportId", report.getId());
        return new ResponseBody(map);
    }

    @RequestMapping(value = "/{reportId}/carrier", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER') or hasRole('EMPLOYEE')")
    public ResponseBody carrier(@PathVariable("reportId") Integer reportId, @Valid @RequestBody CarrierDTO carrierDTO, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return ValidUtils.getFirstErrorInfo(result);
        }
        //经过AuthorizationAspect的权限切面,appCode非空且正确
        Company company = companyRepository.findByAppCode(request.getHeader("appCode"));
        PersonReport report = personReportRepository.findOne(reportId);
        report.setServicePassword(carrierDTO.getServicePassword());
        report.setLinkman1Name(carrierDTO.getLinkman1Name());
        report.setLinkman1Relationship(carrierDTO.getLinkman1Relationship());
        report.setLinkman1Mobile(carrierDTO.getLinkman1Mobile());
        report.setLinkman2Name(carrierDTO.getLinkman2Name());
        report.setLinkman2Relationship(carrierDTO.getLinkman2Relationship());
        report.setLinkman2Mobile(carrierDTO.getLinkman2Mobile());
        personReportRepository.save(report);
        if (MoxieTaskStatusEnum.REPORT_SUCCESS.equals(report.getMoxieTaskCarrierStatus())) {
            moxieCarrierReportService.analyseReport(report);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("reportId", report.getId());
        return new ResponseBody(map);
    }

    /**
     * 增值服务,查询法院判决详细
     *
     * @param reportId 报告Id
     * @param docId    需要查询的法院判决文书Id
     */
    @RequestMapping(value = "/{reportId}/courtJudgmentDetail/{docId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER') or hasRole('EMPLOYEE')")
    public ResponseBody courtJudgmentDetail(@PathVariable("reportId") Integer reportId, @PathVariable("docId") String docId) {
        PersonReport report = personReportRepository.findOne(reportId);
        Optional<CourtJudgment> courtJudgmentOptional = report.getCourtJudgmentPO().getCourtJudgments().stream().filter(e -> e.getDocId().equals(docId)).findFirst();
        if (courtJudgmentOptional.isPresent()) {
            CourtJudgment courtJudgment = courtJudgmentOptional.get();
            if (courtJudgment.isQueried()) {
                throw new FailResponse(ExceptionEnum.REPORT_COURTJUDGMENT_DETAIL_QUERIED);
            } else {
                analyseService.handleCourtJudgmentDetail(courtJudgment);
                if (courtJudgment.isQueried()) {
                    personReportRepository.save(report);
                    return new ResponseBody(courtJudgment);
                } else {
                    throw new FailResponse(ExceptionEnum.REPORT_COURTJUDGMENT_DETAIL_HANDLE_ERROR);
                }
            }
        } else {
            throw new FailResponse(ExceptionEnum.REPORT_COURTJUDGMENT_DETAIL_DOCID_ERROR);
        }
    }
}