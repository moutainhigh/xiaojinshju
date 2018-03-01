package fengkongweishi.entity.customersearchlog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.annotation.Info;
import fengkongweishi.handle.DateTimeSerializer;

import java.util.Date;

/**
 * 查询列表
 *
 * @author duyiting
 * @date 2018/01/17
 */
public class CustomerSearchLogVo {

    private Integer id;
    private String name;
    private String mobile;
    private String idCard;
    private Date lastQueryTime;
    private String status;
    private String level;
    private String createdByName;
    private Integer reportId;
    @Info(label = "备注记录条数", tip = "", placeholder = "", help = "", secret = "")
    private Integer evaluationVOs;
    private String edition;
    private Date reportTime;


    public CustomerSearchLogVo(CustomerSearchLog customerSearchLog) {
        this.id = customerSearchLog.getId();
        this.name = customerSearchLog.getCustomer().getName();
        this.mobile = customerSearchLog.getCustomer().getMobile();
        this.idCard = customerSearchLog.getCustomer().getIdCard();
        this.lastQueryTime = customerSearchLog.getCustomer().getUpdateAt();
        this.status = customerSearchLog.getReport().getStatus().getMessage();
        if (customerSearchLog.getLevel() == null) {
            this.level = null;
        } else {
            this.level = customerSearchLog.getLevel().getMessage();
        }
        this.createdByName = customerSearchLog.getCreateBy().getNickname();
        this.reportId = customerSearchLog.getReport().getId();
        this.evaluationVOs = customerSearchLog.getCustomer().getEvaluations().size();
        this.edition = customerSearchLog.getReport().getEdition().getMessage();
        this.reportTime = customerSearchLog.getReport().getFinishAt();
    }


    public Integer getEvaluationVOs() {
        return evaluationVOs;
    }

    public void setEvaluationVOs(Integer evaluationVOs) {
        this.evaluationVOs = evaluationVOs;
    }

    public Integer getReportId() {
        return reportId;
    }


    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getLastQueryTime() {
        return lastQueryTime;
    }

    public void setLastQueryTime(Date lastQueryTime) {
        this.lastQueryTime = lastQueryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}
