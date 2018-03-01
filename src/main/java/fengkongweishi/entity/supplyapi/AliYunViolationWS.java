package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ali全国车辆交通违章查询(网尚科技)
 *
 * @author huanghengkun
 * @date 2018/01/26
 */
@Component
public class AliYunViolationWS implements ISupplyAPI {
    @Value("${aliyun.api.violation.ws.name}")
    private String name;
    @Value("${aliyun.api.violation.ws.code}")
    private String code;
    @Value("${aliyun.api.violation.ws.effectivetime}")
    private Integer effectiveTime;
    @Value("${aliyun.api.violation.ws.host}")
    private String host;
    @Value("${aliyun.api.violation.ws.path}")
    private String path;
    @Value("${aliyun.api.violation.ws.method}")
    private String method;
    @Value("${aliyun.api.appcode}")
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
