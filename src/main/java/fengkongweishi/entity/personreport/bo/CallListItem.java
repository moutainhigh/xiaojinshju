package fengkongweishi.entity.personreport.bo;

import java.util.Objects;

/**
 * 通信详单
 *
 * @author huanghengkun
 * @date 2018/02/26
 */
public class CallListItem {
    /**
     * 城市
     */
    private String city;
    /**
     * 号码
     */
    private String peerNum;
    /**
     * 公式
     */
    private String companyName;
    /**
     * 通话时长
     */
    private Integer callTime;
    /**
     * 主叫次数
     */
    private Integer dialCnt;
    /**
     * 被叫次数
     */
    private Integer dialedCnt;

    public CallListItem() {
    }

    public CallListItem(String city, String peerNum, String companyName, Integer callTime, Integer dialCnt, Integer dialedCnt) {
        this.city = city;
        this.peerNum = peerNum;
        this.companyName = companyName;
        this.callTime = callTime;
        this.dialCnt = dialCnt;
        this.dialedCnt = dialedCnt;
    }

    @Override
    public String toString() {
        return "CallListItem{" +
                "city='" + city + '\'' +
                ", peerNum='" + peerNum + '\'' +
                ", companyName='" + companyName + '\'' +
                ", callTime=" + callTime +
                ", dialCnt=" + dialCnt +
                ", dialedCnt=" + dialedCnt +
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
        CallListItem that = (CallListItem) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(peerNum, that.peerNum) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(callTime, that.callTime) &&
                Objects.equals(dialCnt, that.dialCnt) &&
                Objects.equals(dialedCnt, that.dialedCnt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, peerNum, companyName, callTime, dialCnt, dialedCnt);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPeerNum() {
        return peerNum;
    }

    public void setPeerNum(String peerNum) {
        this.peerNum = peerNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public Integer getDialCnt() {
        return dialCnt;
    }

    public void setDialCnt(Integer dialCnt) {
        this.dialCnt = dialCnt;
    }

    public Integer getDialedCnt() {
        return dialedCnt;
    }

    public void setDialedCnt(Integer dialedCnt) {
        this.dialedCnt = dialedCnt;
    }
}
