package fengkongweishi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fengkongweishi.entity.supplyapi.ISupplyAPI;
import fengkongweishi.enums.HttpMethod;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author huanghengkun
 * @date 2018/01/10
 */
public class AliyunCallable implements Callable<Map> {

    private ISupplyAPI supplyAPI;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> queries;
    private String body;
    private String host;
    private String path;
    private String appcode;

    public AliyunCallable(ISupplyAPI supplyAPI, HashMap<String, String> headers, HashMap<String, String> queries, String body) {
        super();
        this.supplyAPI = supplyAPI;
        this.method = supplyAPI.getMethod().toUpperCase();
        this.headers = new HashMap<>(headers);
        this.queries = new HashMap<>(queries);
        this.body = body;
        this.host = supplyAPI.getHost();
        this.path = supplyAPI.getPath();
        this.appcode = supplyAPI.getAppCode();
    }

    @Override
    public Map call() {

        // 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
        // 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        if (HttpMethod.POST.toString().equalsIgnoreCase(method)) {
            try {
                HttpResponse response = HttpUtils.doPost(host, path, method, headers, queries, body);
                System.out.println(response.toString());
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(EntityUtils.toString(response.getEntity(), Charset.forName("utf-8")), Map.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            //noinspection Duplicates
            try {
                HttpResponse response = HttpUtils.doGet(host, path, method, headers, queries);
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(EntityUtils.toString(response.getEntity(), Charset.forName("utf-8")), Map.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
