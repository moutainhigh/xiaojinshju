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
public class xsblackhitTest {

    public static void main(String[] args) {
        String host = "http://xsblackhit.market.alicloudapi.com";
        String path = "/ws/black/compBQuery";
        String method = "POST";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> queries = new HashMap<>();
        Map<String, String> bodies = new HashMap<>();
        queries.put("idCard", "330726199107300716");
        queries.put("mobile", "18767988615");
        queries.put("name", "张海东");
        queries.put("sign", "93f8fed75d386ba5bbff2a28bc7a983b");
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
