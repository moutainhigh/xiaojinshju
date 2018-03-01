package fengkongweishi.entity.customersearchlog;

import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.customer.Customer;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.user.User;
import fengkongweishi.enums.ChargeTypeEnum;
import fengkongweishi.enums.Level;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 客户查询记录
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
@Entity
public class CustomerSearchLog extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private Level level;
    /**
     * attention记录本次查询与系统中上一次customer记录的姓名和电话是否存在不一致情况。
     */
    private String attention;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "createBy")
    private User createBy;
    private Date createTime;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private ChargeTypeEnum chargeType;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "reportId")
    private PersonReport report;

    public CustomerSearchLog(Customer customer, String attention, User createBy, Date createTime, Integer price, ChargeTypeEnum chargeType, PersonReport report) {
        this.customer = customer;
        this.attention = attention;
        this.createBy = createBy;
        this.createTime = createTime;
        this.price = price;
        this.chargeType = chargeType;
        this.report = report;
    }

    public CustomerSearchLog() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerSearchLog that = (CustomerSearchLog) o;
        return Objects.equals(customer, that.customer) &&
                level == that.level &&
                Objects.equals(attention, that.attention) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(price, that.price) &&
                chargeType == that.chargeType &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, level, attention, createBy, createTime, price, chargeType, report);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ChargeTypeEnum getChargeType() {
        return chargeType;
    }

    public void setChargeType(ChargeTypeEnum chargeType) {
        this.chargeType = chargeType;
    }

    public PersonReport getReport() {
        return report;
    }

    public void setReport(PersonReport report) {
        this.report = report;
    }
}
