package com.aliyun;

import com.fasterxml.jackson.databind.ObjectMapper;
import fengkongweishi.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huanghengkun
 * @date 2018/01/16
 */
public class xinshujudTest {

    public static void main(String[] args) {
        String host = "http://xinshujud.market.alicloudapi.com";
        String path = "/ws/court/query";
        String method = "POST";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> queries = new HashMap<>();
        Map<String, String> bodies = new HashMap<>();
        queries.put("q", "pname:郑飘飘@idcardNo:330726198111132765");
        queries.put("sign", "dcdb64ad7c4634ffd4d8d900b6b3810c");
        headers.put("Authorization", "APPCODE 41022859e7f84583b81d1fc355130af5");
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, queries, bodies);
            ObjectMapper mapper = new ObjectMapper();
            Map result = mapper.readValue(EntityUtils.toString(response.getEntity(), Charset.forName("utf-8")), Map.class);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}