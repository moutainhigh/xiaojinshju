package fengkongweishi.entity.authorizationlog;

import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.company.Company;
import fengkongweishi.enums.TerminalEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * @author huanghengkun
 * @date 2018/01/16
 */
@Entity
public class AuthorizationLog extends BaseEntity {
    private String appCode;
    @Enumerated(EnumType.STRING)
    private TerminalEnum terminal;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 维度
     */
    private String latitude;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Company company;
    private Date createAt;
    private String ip;

    public AuthorizationLog(String appCode, TerminalEnum terminal, String longitude, String latitude, String ip) {
        this.appCode = appCode;
        this.terminal = terminal;
        this.longitude = longitude;
        this.latitude = latitude;
        this.ip = ip;
        this.createAt = new Date();
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public TerminalEnum getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalEnum terminal) {
        this.terminal = terminal;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
