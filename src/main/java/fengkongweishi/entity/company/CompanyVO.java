package fengkongweishi.entity.company;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.enums.CompanyStatusEnum;
import fengkongweishi.enums.LicenseTypeEnum;
import fengkongweishi.enums.SystemEditionEnum;
import fengkongweishi.handle.DateTimeSerializer;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * company View Object
 *
 * @author duyiting
 * @date 2018/01/10
 */
public class CompanyVO {

    private Integer id;
    private String companyName;
    private String managerName;
    private String managerMobile;
    private Integer remainder = 0;
    private Date verifyTime;
    private Date createTime;
    private CompanyStatusEnum status;
    private List<SystemEditionEnum> openEditions;
    private Integer teamCount;
    private Integer employeeCount;
    private String province;
    private String city;
    private String address;
    private LicenseTypeEnum licenseType;
    private String licenseNumber;
    private String licensePicURL;
    private CompanyVO parent;
    private String appCode;
    private Date lastModifiedTime;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public CompanyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CompanyStatusEnum status) {
        this.status = status;
    }

    public void setParent(CompanyVO parent) {
        this.parent = parent;
    }

    public CompanyVO getParent() {
        return parent;
    }

    public Integer getId() {
        return id;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerMobile() {
        return managerMobile;
    }

    public void setManagerMobile(String managerMobile) {
        this.managerMobile = managerMobile;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public List<SystemEditionEnum> getOpenEditions() {
        return openEditions;
    }

    public void setOpenEditions(List<SystemEditionEnum> openEditions) {
        this.openEditions = openEditions;
    }

    public Integer getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
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


    public CompanyVO(Company company) {
        this.id = company.getId();
        this.appCode = company.getAppCode();
        this.companyName = company.getCompanyName();
        if (company.getManager() == null) {
            this.managerName = null;
            this.managerMobile = null;
        } else {
            this.managerName = company.getManager().getNickname();
            this.managerMobile = company.getManager().getUsername();
        }
        this.remainder = company.getRemainder();
        this.status = company.getStatus();
        this.verifyTime = company.getVerifyTime();
        this.createTime = company.getCreateTime();
        this.openEditions = company.getOpenEditions().stream().sorted().collect(Collectors.toList());
        this.teamCount = company.getChildren().size();
        Integer employeeNumber = company.getEmployees().size();
        if (company.getChildren().size() == 0) {
            employeeNumber = company.getEmployees().size();
        } else {
            Set<Company> children = company.getChildren();
            for (Company company1 : children) {
                employeeNumber += company1.getEmployees().size();
            }
        }
        this.employeeCount = employeeNumber;
        this.province = company.getProvince();
        this.city = company.getCity();
        this.address = company.getAddress();
        this.licenseNumber = company.getLicenseNumber();
        this.licensePicURL = company.getLicensePicURL();
        this.licenseType = company.getLicenseType();
        if (company.getParent() != null) {
            this.parent = new CompanyVO(company.getParent());
        } else {
            this.parent = null;
        }
        this.lastModifiedTime = company.getLastModifiedTime();
    }
}
