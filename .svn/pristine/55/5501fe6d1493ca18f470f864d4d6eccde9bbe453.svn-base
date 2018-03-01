package fengkongweishi.entity.supplyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * alipay行业关注名单普惠版
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Component
public class AlipayWatchList implements ISupplyAPI {
    @Value("${alipay.api.watchlist.name}")
    private String name;
    @Value("${alipay.api.watchlist.code}")
    private String code;
    @Value("${alipay.api.watchlist.effectivetime}")
    private Integer effectiveTime;
    @Value("${alipay.api.watchlist.method}")
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
