package fengkongweishi.entity.personreport.bo;

import fengkongweishi.annotation.Info;

import java.util.Objects;

/**
 * 车辆违章记录
 *
 * @author liuzhenfeng
 * @date 2018/1/29
 */
@Info(label = "车辆违章记录", tip = "", placeholder = "", help = "", secret = "")
public class Violation {

    @Info(label = "违章时间", tip = "", placeholder = "", help = "", secret = "")
    private String time;

    @Info(label = "违章地点", tip = "", placeholder = "", help = "", secret = "")
    private String address;

    @Info(label = "违章原因", tip = "", placeholder = "", help = "", secret = "")
    private String reason;

    @Info(label = "罚款金额", tip = "", placeholder = "", help = "", secret = "")
    private String fine;

    @Info(label = "扣分", tip = "", placeholder = "", help = "", secret = "")
    private String score;

    @Info(label = "车牌号", tip = "", placeholder = "", help = "", secret = "")
    private String plateNumber;

    @Info(label = "发动机号", tip = "", placeholder = "", help = "", secret = "")
    private String engineNo;

    @Override
    public String toString() {
        return "Violation [time=" + time + ", address=" + address + ", reason=" + reason + ", fine=" + fine + ", score="
                + score + ", plateNumber=" + plateNumber + ", engineNo=" + engineNo + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Violation that = (Violation) o;
        return Objects.equals(time, that.time) && Objects.equals(address, that.address)
                && Objects.equals(fine, that.fine) && Objects.equals(score, that.score)
                && Objects.equals(reason, that.reason) && Objects.equals(plateNumber, that.plateNumber)
                && Objects.equals(engineNo, that.engineNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, address, reason, fine, score, plateNumber, engineNo);
    }


    public Violation(String time, String address, String reason, String fine, String score, String plateNumber,
                     String engineNo) {
        super();
        this.time = time;
        this.address = address;
        this.reason = reason;
        this.fine = fine;
        this.score = score;
        this.plateNumber = plateNumber;
        this.engineNo = engineNo;
    }

    public Violation() {
        super();
    }


    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
