package fengkongweishi.service;

import com.alipay.api.internal.util.StringUtils;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.customer.Customer;
import fengkongweishi.entity.customer.CustomerForSearch;
import fengkongweishi.entity.customer.CustomerRepository;
import fengkongweishi.entity.customersearchlog.CustomerSearchLog;
import fengkongweishi.entity.customersearchlog.CustomerSearchLogRepository;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.enums.ChargeTypeEnum;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.enums.Level;
import fengkongweishi.util.FailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huanghengkun
 * @date 2018/01/16
 */
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerSearchLogRepository customerSearchLogRepository;

    @Transactional(rollbackOn = Exception.class)
    public Customer putCustomerAndRecordLog(PersonReport report, int price, ChargeTypeEnum chargeType) {
        Company company = report.getCreateByCompany();
        String idCard = report.getIdCard();
        String name = report.getName();
        String mobile = report.getMobile();
        Customer customerDB = customerRepository.findByCompanyAndIdCard(company, idCard);
        if (customerDB == null) {
            Customer customer = new Customer(company, idCard, name, mobile);
            CustomerSearchLog customerSearchLog = new CustomerSearchLog(customer, null,
                    report.getCreateBy(), new Date(), price, chargeType, report);
            customer.setLatestSearchLog(customerSearchLog);
            customerRepository.save(customer);
            return customer;
        } else {
            List<String> attentionList = new ArrayList<>();
            if (!report.getName().equals(customerDB.getName())) {
                attentionList.add("该身份证对应的姓名与上次不一致");
                customerDB.setName(report.getName());
            }
            if (!report.getMobile().equals(customerDB.getMobile())) {
                attentionList.add("该身份证对应的手机号与上次不一致");
                customerDB.setMobile(report.getMobile());
            }
            customerDB.setUpdateAt(new Date());
            String attention = String.join(",", attentionList);
            CustomerSearchLog customerSearchLog = new CustomerSearchLog(customerDB, attention,
                    report.getCreateBy(), new Date(), price, chargeType, report);
            customerDB.setLatestSearchLog(customerSearchLog);
            customerRepository.save(customerDB);
            return customerDB;
        }
    }

    public Page<Customer> multiConditionSearch(CustomerForSearch searchBean, Pageable pageable, Company company) {

        Page<Customer> customerPage = customerRepository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                Predicate companyEqual = cb.equal(root.get("company"), company);
                predicateList.add(companyEqual);
                if (searchBean != null) {
                    if (searchBean.getCreateBy() != null) {
                        Predicate idEqual = cb.equal(root.get("latestSearchLog").get("createBy").get("id"), searchBean.getCreateBy());
                        predicateList.add(idEqual);
                    }

                    if (!StringUtils.isEmpty(searchBean.getLevel())) {
                        Level level;
                        try {
                            level = Level.valueOf(searchBean.getLevel());
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new FailResponse(ExceptionEnum.LEVEL_PARAM_ERROR);
                        }
                        Predicate levelEqual = cb.equal(root.get("latestSearchLog").get("level"), level);
                        predicateList.add(levelEqual);
                    }

                    if (!StringUtils.isEmpty(searchBean.getDetail())) {
                        Predicate nameEqual = cb.like(root.get("name"), searchBean.getDetail()+"%");
                        Predicate mobileEqual = cb.like(root.get("mobile"), searchBean.getDetail()+"%");
                        Predicate idCardEqual = cb.like(root.get("idCard"), searchBean.getDetail()+"%");
                        Predicate detailEqual = cb.or(nameEqual, mobileEqual, idCardEqual);
                        predicateList.add(detailEqual);
                    }

                    if (null != searchBean.getBeginTime() && null != searchBean.getEndTime()) {
                        Predicate beginTime = cb.greaterThanOrEqualTo(root.get("updateAt"), searchBean.getBeginTime());
                        Predicate endTime = cb.lessThanOrEqualTo(root.get("updateAt"), searchBean.getEndTime());
                        Predicate time = cb.between(root.<Date>get("updateAt"), searchBean.getBeginTime(), searchBean.getEndTime());
                        predicateList.add(time);
                    }
                }
                query.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        }, pageable);
        return customerPage;
    }
}
