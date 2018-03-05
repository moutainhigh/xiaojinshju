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

    private Boolean enabled;

    private CompanyVO company;

    private Boolean superiorMember;

    public UserVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        if(user.getCompany() != null){
            Company company = user.getCompany();
            this.company = new CompanyVO(company);
            this.superiorMember = user.getCompany().getParent() == null;
        }
        this.enabled = user.isEnabled();

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public CompanyVO getCompany() {
        return company;
    }

    public Boolean getSuperiorMember() {
        return superiorMember;
    }
}
