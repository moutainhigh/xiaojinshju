package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * xinshu个人风险信息综合查询(A)
 *
 * @author huanghengkun
 * @date 2018/01/26
 */
@Component
public class XinShuRiskA implements ISupplyAPI {

    @Value("${xinshu.api.risk.A.name}")
    private String name;
    @Value("${xinshu.api.risk.A.code}")
    private String code;
    @Value("${xinshu.api.risk.A.effectivetime}")
    private Integer effectiveTime;
    @Value("${xinshu.api.host}")
    private String host;
    @Value("${xinshu.api.risk.A.path}")
    private String path;
    @Value("${xinshu.api.risk.A.method}")
    private String method;
    @Value("${xinshu.api.secretid}")
    private String secretId;
    @Value("${xinshu.api.secretkey}")
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

