package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ali手机号码归属地
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Component
public class AliYunMobile implements ISupplyAPI {
    @Value("${aliyun.api.mobile.name}")
    private String name;
    @Value("${aliyun.api.mobile.code}")
    private String code;
    @Value("${aliyun.api.mobile.effectivetime}")
    private Integer effectiveTime;
    @Value("${aliyun.api.mobile.host}")
    private String host;
    @Value("${aliyun.api.mobile.path}")
    private String path;
    @Value("${aliyun.api.mobile.method}")
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
