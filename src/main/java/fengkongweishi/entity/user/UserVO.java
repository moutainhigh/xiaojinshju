package fengkongweishi.entity.user;

import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.company.CompanyVO;
import fengkongweishi.entity.role.Role;

/**
 * user View Object
 *
 * @author duyiting
 * @date 2018/01/10
 */
public class UserVO {
    private Integer id;

    private String username;

    private String nickname;

    private Role role;

    private boolean enabled;

    private CompanyVO company;

    public UserVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        if(user.getCompany() != null){
            Company company = user.getCompany();
            CompanyVO companyVO = new CompanyVO(company);
            this.setCompany(companyVO);
        }
        this.enabled = user.isEnabled();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public CompanyVO getCompany() {
        return company;
    }

    public void setCompany(CompanyVO company) {
        this.company = company;
    }
}
