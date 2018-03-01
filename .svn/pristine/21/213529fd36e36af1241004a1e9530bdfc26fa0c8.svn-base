package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * alipay芝麻积分
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Component
public class AlipayZhimaScore implements ISupplyAPI {
    @Value("${alipay.api.zhimascore.name}")
    private String name;
    @Value("${alipay.api.zhimascore.code}")
    private String code;
    @Value("${alipay.api.zhimascore.effectivetime}")
    private Integer effectiveTime;
    @Value("${alipay.api.zhimascore.method}")
    private String method;


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
        return null;
    }

    @Override
    public String getPath() {
        return null;
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
        return null;
    }

    @Override
    public String getSecretKey() {
        return null;
    }
}
