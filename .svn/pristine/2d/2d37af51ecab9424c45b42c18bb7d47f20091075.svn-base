package com.youfen;

import com.fasterxml.jackson.databind.ObjectMapper;
import fengkongweishi.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huanghengkun
 * @date 2018/01/18
 */
public class vehicleTest {
    public static void main(String[] args) {
        String method = "GET";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> queries = new HashMap<>();
        Map<String, String> bodies = new HashMap<>();
        String host = "https://api.acedata.com.cn:2443";
        String path = "/oreo/vehicle/violation/query";
        String appcode = "shangdao041";

        queries.put("account", appcode);

        queries.put("vin", "LSVAT2NG3FN051191");
        queries.put("engineNo", "591032");
        queries.put("licensePlateType", "02");
        queries.put("licensePlateNo", "浙D26W92");

        //noinspection Duplicates
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, queries);
            ObjectMapper mapper = new ObjectMapper();
            Map result = mapper.readValue(EntityUtils.toString(response.getEntity(), Charset.forName("utf-8")), Map.class);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
