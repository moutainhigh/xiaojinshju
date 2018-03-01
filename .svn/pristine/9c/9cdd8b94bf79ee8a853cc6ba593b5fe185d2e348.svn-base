package fengkongweishi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fengkongweishi.entity.supplyapi.ISupplyAPI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author huanghengkun
 * @date 2018/01/26
 */
public class XinShuCallable implements Callable<Map> {
    private ISupplyAPI supplyAPI;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> queries;
    private String body;
    private String host;
    private String path;
    private String secretId;
    private String secretKey;

    public XinShuCallable(ISupplyAPI supplyAPI, HashMap<String, String> headers, final HashMap<String, String> queries, String body) {
        super();
        this.supplyAPI = supplyAPI;
        this.method = supplyAPI.getMethod().toUpperCase();
        this.headers = new HashMap<>(headers);
        this.queries = new HashMap<>(queries);
        this.body = body;
        this.host = supplyAPI.getHost();
        this.path = supplyAPI.getPath();
        this.secretId = supplyAPI.getSecretId();
        this.secretKey = supplyAPI.getSecretKey();
    }

    @Override
    public Map call() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        queries.put("apikey", secretKey);
        queries.put("sign", MD5Util.encode(secretId + sdf.format(new Date())));
        //noinspection Duplicates
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, queries, body);
            //注：如果HTTP状态码为502/503,通常为网络不稳定,请忽略再重试
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("statusCode:" + statusCode);
            if (statusCode == 502 || statusCode == 503) {
                Thread.sleep(2000);
                response = HttpUtils.doPost(host, path, method, headers, queries, body);
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(EntityUtils.toString(response.getEntity(), Charset.forName("utf-8")), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
