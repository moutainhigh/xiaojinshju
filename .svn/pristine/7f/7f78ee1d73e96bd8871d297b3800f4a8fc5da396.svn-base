package fengkongweishi.entity.user;

import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.role.Role;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 用户实体类
 *
 * @author
 * @date
 */
@Entity
public class User extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    private String nickname;

    private String password;
    private boolean enabled;
    private Date createTime;
    private Integer remainder = 0;

    @ManyToOne
    private Role role;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Company company;
    private Date joinCompanyTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void deposit(Integer value) {
        this.remainder = this.remainder + Math.abs(value);
    }

    public void withdraw(Integer value) {
        this.remainder = this.remainder - Math.abs(value);
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return null;
    }

    public String password() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getJoinCompanyTime() {
        return joinCompanyTime;
    }

    public void setJoinCompanyTime(Date joinCompanyTime) {
        this.joinCompanyTime = joinCompanyTime;
    }
}
