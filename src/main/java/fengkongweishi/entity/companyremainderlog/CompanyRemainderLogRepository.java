package fengkongweishi.entity.companyremainderlog;

import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.user.User;
import fengkongweishi.enums.ChargeStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author huanghengkun
 * @date 2018/01/08
 */
public interface CompanyRemainderLogRepository extends JpaRepository<CompanyRemainderLog, Integer> {

    Page<CompanyRemainderLog> findByCompanyAndStatusNotOrderByIdDesc(Company company, ChargeStatusEnum status , Pageable page);

    Integer countByCompanyAndByUser(Company company,User user);

    Page<CompanyRemainderLog> findByCompanyAndStatusOrderByIdDesc(Company company,ChargeStatusEnum status,Pageable page);
}
