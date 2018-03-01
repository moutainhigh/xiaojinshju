package fengkongweishi.entity.customer;

import fengkongweishi.annotation.Info;
import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.customersearchlog.CustomerSearchLog;
import fengkongweishi.entity.evaluate.Evaluation;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 系统查询的客户
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
@Entity
@Table(indexes = {@Index(name = "idx_customer_company_idCard", unique = true, columnList = "companyId,idCard"),
        @Index(name = "idx_customer_updateAt", columnList = "updateAt")})
public class Customer extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private Company company;
    private String idCard;
    private String name;
    private String mobile;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "latestSearchLogId")
    private CustomerSearchLog latestSearchLog;
    private Date updateAt;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<CustomerSearchLog> searchLog;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "toCustomer")
    @Info(label = "备注记录", tip = "", placeholder = "", help = "", secret = "")
    private Set<Evaluation> evaluations;


    public Customer() {
    }

    public Customer(Company company, String idCard, String name, String mobile) {
        this.company = company;
        this.idCard = idCard;
        this.name = name;
        this.mobile = mobile;
        this.updateAt = new Date();
    }
    

    public Set<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(Set<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<CustomerSearchLog> getSearchLog() {
        return searchLog;
    }

    public void setSearchLog(Set<CustomerSearchLog> searchLog) {
        this.searchLog = searchLog;
    }

    public CustomerSearchLog getLatestSearchLog() {
        return latestSearchLog;
    }

    public void setLatestSearchLog(CustomerSearchLog latestSearchLog) {
        this.latestSearchLog = latestSearchLog;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
