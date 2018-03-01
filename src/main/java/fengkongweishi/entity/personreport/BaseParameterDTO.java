package fengkongweishi.entity.personreport;

import fengkongweishi.enums.SystemEditionEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 基础信息查询传输类
 *
 * @author huanghengkun
 * @date 2018/01/16
 */
public class BaseParameterDTO {
    @NotBlank(message = "姓名不能为空")
    @Length(min = 2, max = 5, message = "2-5位中文姓名")
    private String name;
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}", message = "手机号格式错误")
    private String mobile;
    @NotBlank(message = "身份证不能为空")
    @Pattern(regexp = "[0-9]{17}([0-9]|X|x)", message = "18位身份证格式不正确")
    private String idCard;
    @NotBlank(message = "银行卡不能为空")
    @Pattern(regexp = "[0-9]{16,19}", message = "需要16-19位银行卡号")
    private String bankCard;
    @NotBlank(message = "常用地址不能为空")
    private String commonAddress;
    @NotNull(message = "查询版本不能为空")
    private SystemEditionEnum edition;

    @Override
    public String toString() {
        return "BaseParameterDTO{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idCard='" + idCard + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", commonAddress='" + commonAddress + '\'' +
                ", edition=" + edition +
                '}';
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(String commonAddress) {
        this.commonAddress = commonAddress;
    }

    public SystemEditionEnum getEdition() {
        return edition;
    }

    public void setEdition(SystemEditionEnum edition) {
        this.edition = edition;
    }
}
