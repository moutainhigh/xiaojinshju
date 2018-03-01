package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 天御反欺诈
 *
 * @author huanghengkun
 * @date 2018/01/11
 */
@Component
public class CsecAntiFraud implements ISupplyAPI{
    @Value("${csec.api.antifraud.name}")
    private String name;
    @Value("${csec.api.antifraud.code}")
    private String code;
    @Value("${csec.api.antifraud.effectivetime}")
    private Integer effectiveTime;
    @Value("${csec.api.antifraud.host}")
    private String host;
    @Value("${csec.api.antifraud.path}")
    private String path;
    @Value("${csec.api.antifraud.method}")
    private String method;
    @Value("${csec.api.secretid}")
    private String secretId;
    @Value("${csec.api.secretkey}")
    private String secretKey;

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
        return null;
    }

    @Override
    public String getSecretId() {
        return secretId;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }
}
