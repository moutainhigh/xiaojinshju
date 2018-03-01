package fengkongweishi.entity.companyverification;

import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.user.User;
import fengkongweishi.enums.ApplyTypeEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 企业审批
 *
 * @author huanghengkun
 * @date 2018/01/08
 */
@Entity
public class CompanyVerification extends BaseEntity {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Company company;
    private Date applyTime;
    @Enumerated(EnumType.STRING)
    private ApplyTypeEnum applyType;
    @Column(columnDefinition = "text")
    private String applyInfo;
    private Boolean verifyResult;
    private String reason;
    private Date verifyTime;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private User verifyUser;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User applyUser;

    public User getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(User applyUser) {
        this.applyUser = applyUser;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public ApplyTypeEnum getApplyType() {
        return applyType;
    }

    public void setApplyType(ApplyTypeEnum applyType) {
        this.applyType = applyType;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    public Boolean isVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(Boolean verifyResult) {
        this.verifyResult = verifyResult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public User getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(User verifyUser) {
        this.verifyUser = verifyUser;
    }
}
