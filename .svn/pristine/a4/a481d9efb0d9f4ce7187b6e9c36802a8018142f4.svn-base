package fengkongweishi.entity.userremainderlog;

import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.user.User;
import fengkongweishi.enums.ChargeStatusEnum;
import fengkongweishi.enums.ChargeTypeEnum;
import fengkongweishi.enums.PaymentChannelEnum;
import fengkongweishi.enums.PlatformEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 个人用户(非团体)查询记录 预留
 * @author huanghengkun
 * @date 2018/01/08
 */
@Entity
public class UserRemainderLog  extends BaseEntity {

    @ManyToOne
    private User user;

    private Integer entityId;

    private Integer value;

    private Integer realValue;

    @Enumerated(EnumType.STRING)
    private ChargeTypeEnum type;

    private Date createTime;

    @ManyToOne
    private User byUser;

    @Enumerated(EnumType.STRING)
    private PaymentChannelEnum channel;

    private PlatformEnum platform;


    @Enumerated(EnumType.STRING)
    private ChargeStatusEnum status;

    private String tradeNo;


    public void setChannel(PaymentChannelEnum channel) {
        this.channel = channel;
    }

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

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public User getByUser() {
        return byUser;
    }

    public void setByUser(User byUser) {
        this.byUser = byUser;
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
