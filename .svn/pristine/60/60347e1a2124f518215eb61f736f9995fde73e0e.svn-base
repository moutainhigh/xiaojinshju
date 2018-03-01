package fengkongweishi.entity.companyremainderlog;

import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.user.User;
import fengkongweishi.enums.ChargeStatusEnum;
import fengkongweishi.enums.ChargeTypeEnum;
import fengkongweishi.enums.PaymentChannelEnum;
import fengkongweishi.enums.PlatformEnum;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huanghengkun
 * @date 2018/01/08
 */
@Entity
public class CompanyRemainderLog  extends BaseEntity {

    @ManyToOne
    private Company company;

    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "reportId")
    private PersonReport report;

    private Integer value;

    private Integer realValue;

    @Enumerated(EnumType.STRING)
    private ChargeTypeEnum type;

    private Date createTime;

    @ManyToOne
    private User byUser;

    @Enumerated(EnumType.STRING)
    private PaymentChannelEnum channel;

    private String note;

    @Enumerated(EnumType.STRING)
    private ChargeStatusEnum status;

    private String tradeNo;

    @Enumerated(EnumType.STRING)
    private PlatformEnum platform;


    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getRealValue() {
        return realValue;
    }

    public void setRealValue(Integer realValue) {
        this.realValue = realValue;
    }

    public ChargeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ChargeStatusEnum status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PersonReport getReport() {
        return report;
    }

    public void setReport(PersonReport report) {
        this.report = report;
    }

    public User getByUser() {
        return byUser;
    }

    public void setByUser(User byUser) {
        this.byUser = byUser;
    }

    public PaymentChannelEnum getChannel() {
        return channel;
    }

    public void setChannel(PaymentChannelEnum channel) {
        this.channel = channel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getFormatCreateTime() {
        return	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ChargeTypeEnum getType() {
        return type;
    }

    public void setType(ChargeTypeEnum type) {
        this.type = type;
    }


}
