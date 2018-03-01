package fengkongweishi.entity.evaluate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fengkongweishi.entity.customer.Customer;

/**
 * 
 * @author liuzhenfeng
 * @date 2018/1/31
 */
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{
	
	public Page<Evaluation> findByToCustomer(Customer customer,Pageable pageable);
}
