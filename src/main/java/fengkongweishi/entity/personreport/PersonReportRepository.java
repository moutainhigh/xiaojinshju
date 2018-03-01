package fengkongweishi.entity.personreport;

import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huanghengkun
 * @date 2018/01/16
 */
public interface PersonReportRepository extends JpaRepository<PersonReport, Integer> {
    PersonReport findByMoxieTaskCarrier(String moxieTaskCarrier);
    
    PersonReport findByMoxieTaskTaobao(String moxieTaskTaobao);

    Integer countByCreateByCompanyAndCreateBy(Company company,User user);
}
