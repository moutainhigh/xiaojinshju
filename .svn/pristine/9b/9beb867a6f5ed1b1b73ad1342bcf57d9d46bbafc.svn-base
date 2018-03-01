package fengkongweishi.entity.apisearchlog;


import fengkongweishi.entity.base.BaseEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huanghengkun
 * @date 2018/01/10
 */
@Entity(name = "api_search_log")
@Table(indexes = {@Index(name = "idx_api_search_log_apicode_parameters", columnList = "apicode,parameters")})
public class APISearchLog extends BaseEntity {

    private String apicode;

    @Lob
    private String result;

    private String parameters;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;


    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getFormatCreateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getApicode() {
        return apicode;
    }

    public void setApicode(String apicode) {
        this.apicode = apicode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
