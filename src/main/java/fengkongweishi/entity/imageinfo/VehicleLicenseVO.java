package fengkongweishi.entity.imageinfo;

import fengkongweishi.annotation.Info;

/**
 * 行驶证识别结果
 *
 * @author huanghengkun
 * @date 2018/03/03
 */
public class VehicleLicenseVO {
    @Info(label = "车架编号", tip = "", placeholder = "", help = "", secret = "")
    private String vin;

    @Info(label = "发动机编号", tip = "", placeholder = "", help = "", secret = "")
    private String engineNo;

    @Info(label = "车牌号码", tip = "", placeholder = "", help = "", secret = "")
    private String plateNumber;

    @Info(label = "车辆类型", tip = "", placeholder = "", help = "", secret = "默认小型车")
    private String carType;

    @Override
    public String toString() {
        return "VehicleLicenseVO{" +
                "vin='" + vin + '\'' +
                ", engineNo='" + engineNo + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", carType='" + carType + '\'' +
                '}';
    }

    public VehicleLicenseVO(String vin, String engineNo, String plateNumber, String carType) {
        super();
        this.vin = vin;
        this.engineNo = engineNo;
        this.plateNumber = plateNumber;
        this.carType = carType;
    }

    public String getVin() {
        return vin;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getCarType() {
        return carType;
    }
}
