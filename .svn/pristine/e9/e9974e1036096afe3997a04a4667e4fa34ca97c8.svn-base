package com.fengkongweishi.util;

import com.alibaba.fastjson.JSONObject;
import fengkongweishi.util.BaiduAipOcr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author huanghengkun
 * @date 2018/03/02
 */
public class BaiduAipOcrTest {
    public static void main(String[] args) throws IOException {
        BaiduAipOcr instance = BaiduAipOcr.getInstance();
        String imagePath = "/users/huanghengkun/desktop/WechatIMG3.jpeg";
        File image = new File(imagePath);
        InputStream input = new FileInputStream(image);

        byte[] bytes = new byte[input.available()];

        input.read(bytes);
        JSONObject result = instance.vehicleLicense(bytes);
        System.out.println(result);
    }
}
