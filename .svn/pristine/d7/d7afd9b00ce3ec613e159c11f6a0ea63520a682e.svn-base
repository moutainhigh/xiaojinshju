package fengkongweishi.entity.companyverification;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.enums.ApplyTypeEnum;
import fengkongweishi.enums.LicenseTypeEnum;
import fengkongweishi.enums.SystemEditionEnum;
import fengkongweishi.handle.DateTimeSerializer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * companyVerification View Object
 *
 * @author duyiting
 * @date 2018/01/11
 */
public class CompanyVerificationVO {

    private Integer id;
    private String company;
    private String employeeCount;
    private String remainder;
    private List<SystemEditionEnum> openEditions;
    private LicenseTypeEnum licenseType;
    private String licenseNumber;
    private String licensePicURL;
    private Date applyTime;
    private String applyType;
    private String applyInfo;
    private Boolean verifyResult;
    private String reason;
    private Date verifyTime;

    public CompanyVerificationVO(CompanyVerification companyVerification) {
        this.id = companyVerification.getId();
        this.company = companyVerification.getCompany().getCompanyName();
        this.licenseNumber = companyVerification.getCompany().getLicenseNumber();
        this.licenseType = companyVerification.getCompany().getLicenseType();
        this.licensePicURL = companyVerification.getCompany().getLicensePicURL();
        this.applyTime = companyVerification.getApplyTime();
        this.applyType = companyVerification.getApplyType().getMessage();
        if (companyVerification.getVerifyTime() == null) {
            if (ApplyTypeEnum.AUTHENTICATION.equals(companyVerification.getApplyType())) {
                this.remainder = "--";
                this.employeeCount = "--";
                List<SystemEditionEnum> a = new ArrayList<>();
                a.addAll(companyVerification.getCompany().getOpenEditions());
                a.clear();
                this.setOpenEditions(a);
                this.applyInfo = companyVerification.getApplyInfo();
            } else if (ApplyTypeEnum.EDITIONUPGREADE.equals(companyVerification.getApplyType())) {
                Set<SystemEditionEnum> openEditions = companyVerification.getCompany().getOpenEditions();
                List<String> openEditionStr = new ArrayList<>();
                openEditions.stream().sorted().forEachOrdered(item -> openEditionStr.add(item.getMessage()));
                this.applyInfo = "当前版本：" + String.join("、", openEditionStr) + "，" + "申请版本：" + SystemEditionEnum.valueOf(companyVerification.getApplyInfo()).getMessage();

                this.remainder = String.valueOf(companyVerification.getCompany().getRemainder());
                this.employeeCount = String.valueOf(companyVerification.getCompany().getEmployees().size());
                this.openEditions = companyVerification.getCompany().getOpenEditions().stream().sorted().collect(Collectors.toList());
            }
        } else {
            if (ApplyTypeEnum.AUTHENTICATION.equals(companyVerification.getApplyType())) {
                this.applyInfo = companyVerification.getApplyInfo();
            } else {
                this.applyInfo = "申请开通"+SystemEditionEnum.valueOf(companyVerification.getApplyInfo()).getMessage();
            }
            this.remainder = String.valueOf(companyVerification.getCompany().getRemainder());
            this.employeeCount = String.valueOf((companyVerification.getCompany().getEmployees().size()));
            this.openEditions = companyVerification.getCompany().getOpenEditions().stream().sorted().collect(Collectors.toList());
        }


        this.verifyResult = companyVerification.isVerifyResult();
        this.reason = companyVerification.getReason();
        this.verifyTime = companyVerification.getVerifyTime();
    }

    @Override
    public String toString() {
        return "CompanyVerificationVO{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", employeeCount='" + employeeCount + '\'' +
                ", remainder='" + remainder + '\'' +
                ", openEditions=" + openEditions +
                ", licenseType=" + licenseType +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", licensePicURL='" + licensePicURL + '\'' +
                ", applyTime=" + applyTime +
                ", applyType='" + applyType + '\'' +
                ", applyInfo='" + applyInfo + '\'' +
                ", verifyResult=" + verifyResult +
                ", reason='" + reason + '\'' +
                ", verifyTime=" + verifyTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(String employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getRemainder() {
        return remainder;
    }

    public void setRemainder(String remainder) {
        this.remainder = remainder;
    }

    public List<SystemEditionEnum> getOpenEditions() {
        return openEditions;
    }

    public void setOpenEditions(List<SystemEditionEnum> openEditions) {
        this.openEditions = openEditions;
    }

    public String getLicensePicURL() {
        return licensePicURL;
    }

    public void setLicensePicURL(String licensePicURL) {
        this.licensePicURL = licensePicURL;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    public Boolean getVerifyResult() {
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

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }


}
