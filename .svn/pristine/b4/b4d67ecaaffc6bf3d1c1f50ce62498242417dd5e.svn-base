package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ali印刷文字识别_行驶证识别
 *
 * @author huanghengkun
 * @date 2018/01/25
 */
@Component
public class AliYunOcrVehicle implements ISupplyAPI {
    @Value("${aliyun.api.ocrvehicle.name}")
    private String name;
    @Value("${aliyun.api.ocrvehicle.code}")
    private String code;
    @Value("${aliyun.api.ocrvehicle.effectivetime}")
    private Integer effectiveTime;
    @Value("${aliyun.api.ocrvehicle.host}")
    private String host;
    @Value("${aliyun.api.ocrvehicle.path}")
    private String path;
    @Value("${aliyun.api.ocrvehicle.method}")
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
