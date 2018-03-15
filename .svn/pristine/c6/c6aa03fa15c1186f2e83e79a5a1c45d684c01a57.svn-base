package fengkongweishi.service;

import com.alipay.api.internal.util.StringUtils;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.personreport.ReportForSearch;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author duyiting
 * @date 2018/03/13
 */
@Service
public class ReportManageService {

    @Autowired
    PersonReportRepository personReportRepository;


    public Page<PersonReport> multiConditionSearch(ReportForSearch searchBean, Pageable pageable, Company company) {

        Page<PersonReport> personReportPage = personReportRepository.findAll(new Specification<PersonReport>() {
            @Override
            public Predicate toPredicate(Root<PersonReport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                Predicate com = cb.equal(root.get("createByCompany"),company);
                Predicate parentCom = cb.equal(root.get("createByCompany").get("parent"),company);
                Predicate compOr = cb.or(com,parentCom);
                predicateList.add(compOr);

                if (searchBean != null) {
                    if (searchBean.getCreatedBy() != null) {
                        Predicate idEqual = cb.equal(root.get("customerSearchLog").get("createBy").get("id"), searchBean.getCreatedBy());
                        predicateList.add(idEqual);
                    }

                    if(searchBean.getCreatedByCompany() != null){
                        Predicate companyEqual = cb.equal(root.get("createByCompany").get("id"), searchBean.getCreatedByCompany());
//                        Predicate parentCom = cb.equal(root.get("createByCompany").get("parent").get("companyName"),searchBean.getCreateByCompany());
//                        Predicate companyOr = cb.or(companyEqual,parentCom);
                        predicateList.add(companyEqual);
                    }

                    if (!StringUtils.isEmpty(searchBean.getLevel())) {
                        Level level;
                        try {
                            level = Level.valueOf(searchBean.getLevel());
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new FailResponse(ExceptionEnum.LEVEL_PARAM_ERROR);
                        }
                        Predicate levelEqual = cb.equal(root.get("level"), level);
                        predicateList.add(levelEqual);
                    }

                    if (!StringUtils.isEmpty(searchBean.getName()) && searchBean.getName() != null) {
                        Predicate nameEqual = cb.like(root.get("name"), searchBean.getName()+"%");
                        predicateList.add(nameEqual);
                    }

                    if (null != searchBean.getBeginTime() && null != searchBean.getEndTime()) {
                        Predicate time = cb.between(root.<Date>get("customerSearchLog").get("createTime"), searchBean.getBeginTime(), searchBean.getEndTime());
                        predicateList.add(time);
                    }

                    if(!StringUtils.isEmpty(searchBean.getNo())){
                        Predicate no = cb.equal(root.get("no"),searchBean.getNo());
                        predicateList.add(no);
                    }
                }
                query.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        }, pageable);
        return personReportPage;
    }
}
