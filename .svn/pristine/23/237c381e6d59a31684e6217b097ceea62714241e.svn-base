package fengkongweishi.entity.personreport;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.enums.Level;
import fengkongweishi.handle.DateTimeSerializer;

import java.util.Date;

/**
 * @author duyiting
 * @date 2018/03/13
 */
public class ReportVO {

    private Integer reportId;
    private String no;
    private String name;
    private String idCard;
    private String createdByCompany;
    private String createdBy;
    private String status;
    private Level level;
    private Date queryTime;

    public ReportVO(PersonReport personReport) {
        this.reportId = personReport.getId();
        this.no = personReport.getNo();
        this.name = personReport.getName();
        this.idCard = personReport.getIdCard();
        this.createdByCompany = personReport.getCreateByCompany().getCompanyName();
        this.createdBy = personReport.getCreateBy().getNickname();
        this.status = personReport.getStatus().getMessage();
        this.level = personReport.getLevel();
        this.queryTime = personReport.getCustomerSearchLog().getCreateTime();
    }

    public Integer getReportId() {
        return reportId;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getCreatedByCompany() {
        return createdByCompany;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getStatus() {
        return status;
    }

    public Level getLevel() {
        return level;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getQueryTime() {
        return queryTime;
    }
}
