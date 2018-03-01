package fengkongweishi.entity.customer;

import fengkongweishi.entity.company.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * @author huanghengkun
 * @date 2018/01/16
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> ,JpaSpecificationExecutor<Customer> {
    Customer findByCompanyAndIdCard(Company company, String idCard);

    Page<Customer> findByCompany(Company company, Pageable page);
    
}
