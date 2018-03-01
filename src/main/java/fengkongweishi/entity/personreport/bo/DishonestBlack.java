package fengkongweishi.entity.personreport.bo;

import java.util.Objects;

/**
 * 失信记录
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class DishonestBlack {
    /**
     * 生效法律文书确定的义务
     */
    private String duty;
    /**
     * 具体情形
     */
    private String disruptType;
    /**
     * 执行依据文号
     */
    private String code;
    /**
     * 公告日期
     */
    private String pubTime;
    /**
     * 执行法院
     */
    private String court;
    /**
     * 省份
     */
    private String area;
    /**
     * 被执行人履行情况
     */
    private String performance;

    @Override
    public String toString() {
        return "DishonestBlack{" +
                "duty='" + duty + '\'' +
                ", disruptType='" + disruptType + '\'' +
                ", code='" + code + '\'' +
                ", pubTime=" + pubTime +
                ", court='" + court + '\'' +
                ", area='" + area + '\'' +
                ", performance='" + performance + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DishonestBlack that = (DishonestBlack) o;
        return Objects.equals(duty, that.duty) &&
                Objects.equals(disruptType, that.disruptType) &&
                Objects.equals(code, that.code) &&
                Objects.equals(pubTime, that.pubTime) &&
                Objects.equals(court, that.court) &&
                Objects.equals(area, that.area) &&
                Objects.equals(performance, that.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duty, disruptType, code, pubTime, court, area, performance);
    }

    public DishonestBlack(String duty, String disruptType, String code, String pubTime, String court, String area, String performance) {
        this.duty = duty;
        this.disruptType = disruptType;
        this.code = code;
        this.pubTime = pubTime;
        this.court = court;
        this.area = area;
        this.performance = performance;
    }

    public DishonestBlack() {
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDisruptType() {
        return disruptType;
    }

    public void setDisruptType(String disruptType) {
        this.disruptType = disruptType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

}
