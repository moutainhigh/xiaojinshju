package fengkongweishi.service;

import com.alibaba.fastjson.JSONObject;
import fengkongweishi.entity.imageinfo.VehicleLicenseVO;
import fengkongweishi.service.report.FetchService;
import fengkongweishi.util.BaiduAipOcr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * 图片识别服务
 *
 * @author huanghengkun
 * @date 2018/03/03
 */
@Service
public class ImageInfoService {

    @Autowired
    private FetchService fetchService;

    /**
     * 解析阿里云返回结果
     *
     * @param base64Code 图片base64码
     * @return 行驶证封装对象
     * @throws IOException
     */
    public VehicleLicenseVO handleAliVehicleLicense(String base64Code) throws IOException {
        Map map = fetchService.fetchOcrVehicle(base64Code);
        if (map == null || !(boolean) map.get("success")) {
            return new VehicleLicenseVO(null, null, null, null);
        }
        String vin = (String) map.get("vin");
        String engineNo = (String) map.get("engine_num");
        String plateNumber = (String) map.get("plate_num");
        String carType = (String) map.get("vehicle_type");
        return new VehicleLicenseVO(vin, engineNo, plateNumber, carType);
    }

    /**
     * 解析百度云返回结果
     *
     * @param bytes 图片二进制数组
     * @return 行驶证封装对象
     * @throws IOException
     */
    public VehicleLicenseVO handleBaiduVehicleLicense(byte[] bytes) throws IOException {
        BaiduAipOcr instance = BaiduAipOcr.getInstance();
        JSONObject ocrResult = instance.vehicleLicense(bytes);
        if (ocrResult == null) {
            return new VehicleLicenseVO(null, null, null, null);
        }
        JSONObject wordsResult = ocrResult.getJSONObject("words_result");
        String vin = wordsResult.getJSONObject("车辆识别代号").getString("words");
        String engineNo = wordsResult.getJSONObject("发动机号码").getString("words");
        String plateNumber = wordsResult.getJSONObject("号牌号码").getString("words");
        String carType = wordsResult.getJSONObject("车辆类型").getString("words");
        return new VehicleLicenseVO(vin, engineNo, plateNumber, carType);
    }
}
