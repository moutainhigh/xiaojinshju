package fengkongweishi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;

import java.util.HashMap;

/**
 * 百度文字识别
 *
 * @author huanghengkun
 * @date 2018/03/02
 */
public class BaiduAipOcr {

    private static final String APP_ID = "10873875";
    private static final String API_KEY = "0vHsNhDwYUfXKsH95ByzECdl";
    private static final String SECRET_KEY = "R4w8CtzpqLffUtLGPC8exGruLcKzKTAF";

    /**
     * 身份证含照片的一面
     */
    public static final String IDCARD_SIDE_FRONT = "front";
    /**
     * 身份证带国徽的一面
     */
    public static final String IDCARD_SIDE_BACK = "back";

    private AipOcr client;

    private static BaiduAipOcr instance = new BaiduAipOcr();

    private BaiduAipOcr() {
        this.client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
    }

    public static BaiduAipOcr getInstance() {
        return instance;
    }

    /**
     * 行驶证识别
     *
     * @param image 行驶证二进制数组
     * @return
     */
    public JSONObject vehicleLicense(byte[] image) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("detect_direction", "true");
        options.put("accuracy", "normal");
        //将org.json.JSONObject转成fastjson
        return JSON.parseObject(client.vehicleLicense(image, options).toString());
    }

    public JSONObject idcard(byte[] image, String idCardSide) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        //将org.json.JSONObject转成fastjson
        return JSON.parseObject(client.idcard(image, idCardSide, options).toString());
    }

    public JSONObject drivingLicense(byte[] image) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        //是否检测图像朝向
        options.put("detect_direction", "false");
        //将org.json.JSONObject转成fastjson
        return JSON.parseObject(client.drivingLicense(image, options).toString());
    }
}
