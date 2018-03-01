package fengkongweishi.entity.personreport;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;

/**
 * @author huanghengkun
 * @date 2018/01/20
 */
public class CarrierDTO {
    @NotBlank(message = "运营商服务密码不能为空")
    private String servicePassword;
    @NotBlank(message = "联系人姓名不能为空")
    @Length(min = 2, max = 5, message = "2-5位中文姓名")
    private String linkman1Name;
    private String linkman1Relationship;
    @NotBlank(message = "联系人手机不能为空")
    @Digits(integer = 11, fraction = 0, message = "需要11位整数数字")
    private String linkman1Mobile;
    @NotBlank(message = "联系人姓名不能为空")
    @Length(min = 2, max = 5, message = "2-5位中文姓名")
    private String linkman2Name;
    private String linkman2Relationship;
    @NotBlank(message = "联系人手机不能为空")
    @Digits(integer = 11, fraction = 0, message = "需要11位整数数字")
    private String linkman2Mobile;

    @Override
    public String toString() {
        return "CarrierDTO{" +
                " servicePassword='" + servicePassword + '\'' +
                ", linkman1Name='" + linkman1Name + '\'' +
                ", linkman1Relationship='" + linkman1Relationship + '\'' +
                ", linkman1Mobile='" + linkman1Mobile + '\'' +
                ", linkman2Name='" + linkman2Name + '\'' +
                ", linkman2Relationship='" + linkman2Relationship + '\'' +
                ", linkman2Mobile='" + linkman2Mobile + '\'' +
                '}';
    }

    public String getServicePassword() {
        return servicePassword;
    }

    public void setServicePassword(String servicePassword) {
        this.servicePassword = servicePassword;
    }

    public String getLinkman1Name() {
        return linkman1Name;
    }

    public void setLinkman1Name(String linkman1Name) {
        this.linkman1Name = linkman1Name;
    }

    public String getLinkman1Relationship() {
        return linkman1Relationship;
    }

    public void setLinkman1Relationship(String linkman1Relationship) {
        this.linkman1Relationship = linkman1Relationship;
    }

    public String getLinkman1Mobile() {
        return linkman1Mobile;
    }

    public void setLinkman1Mobile(String linkman1Mobile) {
        this.linkman1Mobile = linkman1Mobile;
    }

    public String getLinkman2Name() {
        return linkman2Name;
    }

    public void setLinkman2Name(String linkman2Name) {
        this.linkman2Name = linkman2Name;
    }

    public String getLinkman2Relationship() {
        return linkman2Relationship;
    }

    public void setLinkman2Relationship(String linkman2Relationship) {
        this.linkman2Relationship = linkman2Relationship;
    }

    public String getLinkman2Mobile() {
        return linkman2Mobile;
    }

    public void setLinkman2Mobile(String linkman2Mobile) {
        this.linkman2Mobile = linkman2Mobile;
    }

}
