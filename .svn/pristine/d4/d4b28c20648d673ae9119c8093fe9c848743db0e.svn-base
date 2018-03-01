package fengkongweishi.entity.companyverification;

import fengkongweishi.entity.company.Company;
import fengkongweishi.enums.ApplyTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author huanghengkun
 * @date 2018/01/08
 */
public interface CompanyVerificationRepository extends JpaRepository<CompanyVerification,Integer> {

    List<CompanyVerification> findByCompanyAndApplyType(Company company, ApplyTypeEnum applyType);
}
