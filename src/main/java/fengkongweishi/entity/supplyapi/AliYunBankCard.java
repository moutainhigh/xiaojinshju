package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ali银行卡信息查询带归属地
 *
 * @author huanghengkun
 * @date 2018/01/25
 */
@Component
public class AliYunBankCard implements ISupplyAPI {
    @Value("${aliyun.api.bankcard.name}")
    private String name;
    @Value("${aliyun.api.bankcard.code}")
    private String code;
    @Value("${aliyun.api.bankcard.effectivetime}")
    private Integer effectiveTime;
    @Value("${aliyun.api.bankcard.host}")
    private String host;
    @Value("${aliyun.api.bankcard.path}")
    private String path;
    @Value("${aliyun.api.bankcard.method}")
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
