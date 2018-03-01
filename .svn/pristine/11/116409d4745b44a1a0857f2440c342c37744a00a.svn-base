package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * youfen多头借贷全量核查
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Component
public class YouFenMultipleHeadLend implements ISupplyAPI {
    @Value("${youfen.api.multipleheadlend.name}")
    private String name;
    @Value("${youfen.api.multipleheadlend.code}")
    private String code;
    @Value("${youfen.api.multipleheadlend.effectivetime}")
    private Integer effectiveTime;
    @Value("${youfen.api.host}")
    private String host;
    @Value("${youfen.api.multipleheadlend.path}")
    private String path;
    @Value("${youfen.api.multipleheadlend.method}")
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
