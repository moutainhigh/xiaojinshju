package fengkongweishi.controller;

import com.alibaba.fastjson.JSON;
import fengkongweishi.annotation.Info;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.service.report.FetchService;
import fengkongweishi.util.FailResponse;
import fengkongweishi.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author jianger
 * @Description 识别图片
 * @date 2018/1/29 下午3:52
 **/
@RestController
@RequestMapping("/image")
public class ImageInfoController {
    @Autowired
    private FetchService fetchService;

    /**
     * 识别上传行驶证
     *
     * @param file 行驶证
     */
    @RequestMapping(value = "/distinguish", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER') or hasRole('EMPLOYEE')")
    public ResponseBody distinguish(@RequestParam(value = "file") MultipartFile file) {
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            String base64Code = encoder.encode(file.getBytes());
            Map result = fetchService.fetchOcrVehicle(base64Code);
            Map map = handleResult(result);
            VehicleLicenseVO vehicleLicenseVO = new VehicleLicenseVO((String) map.get("vin"), (String) map.get("engine_num"), (String) map.get("plate_num"), (String) map.get("vehicle_type"));
            return new ResponseBody(vehicleLicenseVO);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailResponse(ExceptionEnum.IMAGE_IO_ERROR);
        }
    }

    /**
     * 解析阿里云返回结果
     *
     * @param map
     */
    private Map handleResult(Map<String, Object> map) {
        for (Map.Entry<String, Object> m : map.entrySet()) {
            if (("outputs").equals(m.getKey())) {
                List<Map> list = (List) m.getValue();
                Map data = null;
                for (Map maps : list) {
                    Map result = (Map) maps.get("outputValue");
                    String s = (String) result.get("dataValue");
                    data = (Map) JSON.parse(s);
                    Boolean requestSuccess = (Boolean) data.get("success");
                    if (requestSuccess) {
                        //请求接口成功，不一定识别行驶证成功
                        data.put("success", "请求成功");
                    }
                }
                return data;
            }
        }
        return null;
    }

    /**
     * 行驶证识别结果
     *
     * @author liuzhenfeng
     * @date 2018/1/30
     */

    @Info(label = "行驶证识别结果", tip = "", placeholder = "", help = "", secret = "")
    public static class VehicleLicenseVO {

        @Info(label = "车架编号", tip = "", placeholder = "", help = "", secret = "")
        private String vin;

        @Info(label = "发动机编号", tip = "", placeholder = "", help = "", secret = "")
        private String engineNo;

        @Info(label = "车牌号码", tip = "", placeholder = "", help = "", secret = "")
        private String plateNumber;

        @Info(label = "车辆类型", tip = "", placeholder = "", help = "", secret = "默认小型车")
        private String carType = "02";

        @Override
        public String toString() {
            return "VehicleLicenseVO [vin=" + vin + ", engineNo=" + engineNo + ", plateNumber=" + plateNumber + ", carType="
                    + carType + "]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            VehicleLicenseVO that = (VehicleLicenseVO) o;
            return Objects.equals(vin, that.vin) && Objects.equals(engineNo, that.engineNo)
                    && Objects.equals(plateNumber, that.plateNumber) && Objects.equals(carType, that.carType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vin, engineNo, plateNumber, carType);
        }

        public VehicleLicenseVO(String vin, String engineNo, String plateNumber, String carType) {
            super();
            this.vin = vin;
            this.engineNo = engineNo;
            this.plateNumber = plateNumber;
            this.carType = carType;
        }

        public VehicleLicenseVO() {
            super();
        }

        public String getvin() {
            return vin;
        }

        public void setvin(String vin) {
            this.vin = vin;
        }

        public String getEngineNo() {
            return engineNo;
        }

        public void setEngineNo(String engineNo) {
            this.engineNo = engineNo;
        }

        public String getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

    }
}
