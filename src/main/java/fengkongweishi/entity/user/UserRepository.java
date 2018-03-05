package fengkongweishi.entity.user;

import fengkongweishi.entity.company.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {
	User findByUsername(String username);
	Page<User> findByCompany(Company company, Pageable page);
	List<User> findByCompanyAndIdNotOrderByRole(Company company, Integer Id);
	//员工数量
//	@Query(vaue="select count(*) from User u where u.role.name='ROLE_USER' ")
//    Integer getAllStuffCount();
	
	
}
