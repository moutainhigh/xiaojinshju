package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ali失信黑名单
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Component
public class AliYunDishonestBlack implements ISupplyAPI{
    @Value("${aliyun.api.dishonestblack.name}")
    private String name;
    @Value("${aliyun.api.dishonestblack.code}")
    private String code;
    @Value("${aliyun.api.dishonestblack.effectivetime}")
    private Integer effectiveTime;
    @Value("${aliyun.api.dishonestblack.host}")
    private String host;
    @Value("${aliyun.api.dishonestblack.path}")
    private String path;
    @Value("${aliyun.api.dishonestblack.method}")
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
