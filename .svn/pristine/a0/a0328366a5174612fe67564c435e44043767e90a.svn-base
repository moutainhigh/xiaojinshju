package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * youfen刑事案底核查
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Component
public class YouFenCriminal implements ISupplyAPI {
    @Value("${youfen.api.criminal.name}")
    private String name;
    @Value("${youfen.api.criminal.code}")
    private String code;
    @Value("${youfen.api.criminal.effectivetime}")
    private Integer effectiveTime;
    @Value("${youfen.api.host}")
    private String host;
    @Value("${youfen.api.criminal.path}")
    private String path;
    @Value("${youfen.api.criminal.method}")
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
