package fengkongweishi.service;

import fengkongweishi.entity.role.Role;
import fengkongweishi.entity.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 角色服务
 *
 * @author huanghengkun
 * @date 2018/01/08
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    public void sqlInit() {
        if (roleRepository.count() == 0) {
            Role admin = new Role("ROLE_ADMIN");
            Role manager = new Role("ROLE_MANAGER");
            Role leader = new Role("ROLE_LEADER");
            Role employee = new Role("ROLE_EMPLOYEE");
            Role user = new Role("ROLE_USER");
            roleRepository.save(admin);
            roleRepository.save(manager);
            roleRepository.save(leader);
            roleRepository.save(employee);
            roleRepository.save(user);
        }
    }
}
