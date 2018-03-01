package fengkongweishi.entity.companyremainderlog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.enums.ChargeTypeEnum;
import fengkongweishi.handle.DateTimeSerializer;

import java.util.Date;

/**
 * 公司消费显示
 *
 * @author duyiting
 * @date 2018/01/15
 */
public class CompanyRemainderLogVO {

    private Integer id;
    private String chargeType;//消费分类
    private String value;//消费金额
    private Date createTime;//消费时间
    private String nickname;//操作人姓名
    private String username;//操作人联系方式

    public CompanyRemainderLogVO(CompanyRemainderLog companyRemainderLog) {
        this.id = companyRemainderLog.getId();
        if(ChargeTypeEnum.DEPOSIT.equals(companyRemainderLog.getType())){
            this.chargeType = companyRemainderLog.getType().getMessage();
            this.value = "+"+String.valueOf(companyRemainderLog.getValue());
        }else {
            this.chargeType = "查询"+companyRemainderLog.getChannel().getMessage();
            this.value = "-"+String.valueOf(companyRemainderLog.getValue());
        }
        this.createTime = companyRemainderLog.getCreateTime();
        this.nickname = companyRemainderLog.getByUser().getNickname();
        this.username = companyRemainderLog.getByUser().getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
