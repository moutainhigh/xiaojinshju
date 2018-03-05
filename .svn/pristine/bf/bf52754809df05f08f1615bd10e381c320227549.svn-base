package fengkongweishi.entity.company;

import fengkongweishi.enums.LicenseTypeEnum;
import fengkongweishi.enums.SystemEditionEnum;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author huanghengkun
 * @date 2018/01/23
 */
public class CompanyRegister {
    @NotBlank(message = "公司名字不能为空")
    private String companyName;
    @NotBlank(message = "所在省不能为空")
    private String province;
    @NotBlank(message = "所在市不能为空")
    private String city;
    @NotBlank(message = "详细地址不能为空")
    private String address;
    @NotNull(message = "证件类型不能为空")
    private LicenseTypeEnum licenseType;
    @NotBlank(message = "证件号码不能为空")
    private String licenseNumber;
    @NotBlank(message = "证件图片不能为空")
    private String licensePicURL;
    @NotEmpty(message = "请至少选择一个版本")
    private Set<SystemEditionEnum> openEditions;

    /**
     * 是否开通微信公众号,预留字段
     */
    private Boolean openWeChatSubscription;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LicenseTypeEnum getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseTypeEnum licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicensePicURL() {
        return licensePicURL;
    }

    public void setLicensePicURL(String licensePicURL) {
        this.licensePicURL = licensePicURL;
    }

    public Set<SystemEditionEnum> getOpenEditions() {
        return openEditions;
    }

    public void setOpenEditions(Set<SystemEditionEnum> openEditions) {
        this.openEditions = openEditions;
    }

    public Boolean getOpenWeChatSubscription() {
        return openWeChatSubscription;
    }

    public void setOpenWeChatSubscription(Boolean openWeChatSubscription) {
        this.openWeChatSubscription = openWeChatSubscription;
    }
}
