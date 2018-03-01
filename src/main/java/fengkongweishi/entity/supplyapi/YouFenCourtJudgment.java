package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * youfen个人法院判决简项
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Component
public class YouFenCourtJudgment implements ISupplyAPI {
    @Value("${youfen.api.courtjudgment.name}")
    private String name;
    @Value("${youfen.api.courtjudgment.code}")
    private String code;
    @Value("${youfen.api.courtjudgment.effectivetime}")
    private Integer effectiveTime;
    @Value("${youfen.api.host}")
    private String host;
    @Value("${youfen.api.courtjudgment.path}")
    private String path;
    @Value("${youfen.api.courtjudgment.method}")
    private String method;
    @Value("${youfen.api.appcode}")
    private String appCode;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Integer getEffectiveTime() {
        return effectiveTime;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getAppCode() {
        return appCode;
    }

    @Override
    public String getSecretId() {
        return null;
    }

    @Override
    public String getSecretKey() {
        return null;
    }
}
