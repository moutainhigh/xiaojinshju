package fengkongweishi.entity.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.companyremainderlog.CompanyRemainderLogRepository;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.role.Role;
import fengkongweishi.handle.DateTimeSerializer;

import java.util.Date;

/**
 * Employees View Object
 *
 * @author duyiting
 * @date 2018/01/12
 */
public class EmployeeVO {


    private Integer id;

    private String nickname;
    private String companyName;
    private String username;
    private Integer queryCount;
    private Date joinCompanyTime;
    private Role role;
    public EmployeeVO(User user, PersonReportRepository personReportRepository) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
//        Integer count = companyRemainderLogRepository.findByCompanyAndUser(user.getCompany(),user);
        Company company = user.getCompany();
        this.companyName = company.getCompanyName();
        this.queryCount = personReportRepository.countByCreateByCompanyAndCreateBy(company,user);
        this.joinCompanyTime = user.getJoinCompanyTime();
        this.role = user.getRole();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(Integer queryCount) {
        this.queryCount = queryCount;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getJoinCompanyTime() {
        return joinCompanyTime;
    }

    public void setJoinCompanyTime(Date joinCompanyTime) {
        this.joinCompanyTime = joinCompanyTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
