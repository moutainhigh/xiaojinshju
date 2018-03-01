package fengkongweishi.entity.moxiecallbacklog;

import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.enums.MoxieTaskStatusEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * @author huanghengkun
 * @date 2018/01/19
 */
@Entity
@Table(indexes = {@Index(name = "idx_moxie_callback_log_taskId_status", columnList = "taskId,status")})
public class MoxieCallbackLog extends BaseEntity {
    private String taskId;
    private String type;
    private String event;
    @Enumerated(EnumType.STRING)
    private MoxieTaskStatusEnum status;
    @Column(columnDefinition = "text")
    private String header;
    @Column(columnDefinition = "text")
    private String body;
    private Date receiveAt;

    public MoxieCallbackLog() {
    }

    public MoxieCallbackLog(String taskId, String type, String event, MoxieTaskStatusEnum status, String header, String body) {
        this.taskId = taskId;
        this.type = type;
        this.event = event;
        this.status = status;
        this.header = header;
        this.body = body;
        this.receiveAt = new Date();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public MoxieTaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MoxieTaskStatusEnum status) {
        this.status = status;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getReceiveAt() {
        return receiveAt;
    }

    public void setReceiveAt(Date receiveAt) {
        this.receiveAt = receiveAt;
    }
}
