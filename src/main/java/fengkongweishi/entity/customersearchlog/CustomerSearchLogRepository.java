package fengkongweishi.entity.customersearchlog;

import fengkongweishi.entity.customer.Customer;
import fengkongweishi.entity.personreport.PersonReport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huanghengkun
 * @date 2018/01/16
 */
public interface CustomerSearchLogRepository extends JpaRepository<CustomerSearchLog, Integer> {
    CustomerSearchLog findByReport(PersonReport report);
    
    Page<CustomerSearchLog> findByCustomer(Customer customer,Pageable pageable);
}
