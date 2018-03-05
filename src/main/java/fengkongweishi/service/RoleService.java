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
            Role admin = new Role(Role.defaultRole.ADMIN.getName());
            Role manager = new Role(Role.defaultRole.MANAGER.getName());
            Role leader = new Role(Role.defaultRole.LEADER.getName());
            Role employee = new Role(Role.defaultRole.EMPLOYEE.getName());
            Role user = new Role(Role.defaultRole.USER.getName());
            roleRepository.save(admin);
            roleRepository.save(manager);
            roleRepository.save(leader);
            roleRepository.save(employee);
            roleRepository.save(user);
        }
    }
}
