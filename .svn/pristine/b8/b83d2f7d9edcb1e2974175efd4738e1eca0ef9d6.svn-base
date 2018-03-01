package fengkongweishi.entity.personreport.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import fengkongweishi.enums.Color;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 运营商po对象
 *
 * @author huanghengkun
 * @date 2018/02/26
 */
@Entity
@Table(name = "po_carrier")
public class CarrierPO extends BaseAnalyseItem {
    /**
     * 实名认证
     */
    private String reliability;
    /**
     * 姓名匹配
     */
    private String nameMatch;
    /**
     * 现居地
     */
    private String liveAddress;
    /**
     * 网龄
     */
    private Integer inTime;
    /**
     * 套餐
     */
    private String packageName;
    /**
     * 月均流量使用 M/月
     */
    private Integer netUsedPerMonth;
    /**
     * 日均通话次数 次/天
     */
    private Double callCountPerDay;
    /**
     * 次均通话时长 秒/次
     */
    private Integer callTimePerTime;
    /**
     * 月均话费 元/月
     */
    private Double moneyPerMonth;
    /**
     * 查询过该用户的相关企业数量
     */
    private Integer searchedOrganizationCount;
    /**
     * 查询过该用户的相关企业类型
     */
    private String searchedOrganizationType;
    /**
     * 催收类号码
     */
    private String contactCollection;
    /**
     * 贷款类号码
     */
    private String contactLoan;
    /**
     * 信用卡类
     */
    private String contactCreditCard;
    /**
     * 银行类
     */
    private String contactBank;
    /**
     * 通话概况
     */
    private String phoneCall;
    /**
     * 互通过的电话号码
     */
    private String contactEachOther;
    /**
     * 朋友圈
     */
    private String regularCircle;
    /**
     * 直接联系人中黑名单人数
     */
    private Integer contactsClass1BlacklistCnt;
    /**
     * 间接联系人中黑名单人数
     */
    private Integer contactsClass2BlacklistCnt;
    /**
     * 关机情况
     */
    private String phoneSilent;
    /**
     * 联系人分析
     */
    @Column(columnDefinition = "text")
    private String linkmanChecks;
    /**
     * 通话详单
     */
    @Column(columnDefinition = "text")
    private String callList;

    public CarrierPO() {
    }

    public CarrierPO(Color color) {
        super(color);
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getNameMatch() {
        return nameMatch;
    }

    public void setNameMatch(String nameMatch) {
        this.nameMatch = nameMatch;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public Integer getInTime() {
        return inTime;
    }

    public void setInTime(Integer inTime) {
        this.inTime = inTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getNetUsedPerMonth() {
        return netUsedPerMonth;
    }

    public void setNetUsedPerMonth(Integer netUsedPerMonth) {
        this.netUsedPerMonth = netUsedPerMonth;
    }

    public Double getCallCountPerDay() {
        return callCountPerDay;
    }

    public void setCallCountPerDay(Double callCountPerDay) {
        this.callCountPerDay = callCountPerDay;
    }

    public Integer getCallTimePerTime() {
        return callTimePerTime;
    }

    public void setCallTimePerTime(Integer callTimePerTime) {
        this.callTimePerTime = callTimePerTime;
    }

    public Double getMoneyPerMonth() {
        return moneyPerMonth;
    }

    public void setMoneyPerMonth(Double moneyPerMonth) {
        this.moneyPerMonth = moneyPerMonth;
    }

    public Integer getSearchedOrganizationCount() {
        return searchedOrganizationCount;
    }

    public void setSearchedOrganizationCount(Integer searchedOrganizationCount) {
        this.searchedOrganizationCount = searchedOrganizationCount;
    }

    public String getSearchedOrganizationType() {
        return searchedOrganizationType;
    }

    public void setSearchedOrganizationType(String searchedOrganizationType) {
        this.searchedOrganizationType = searchedOrganizationType;
    }

    public String getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(String contactCollection) {
        this.contactCollection = contactCollection;
    }

    public String getContactLoan() {
        return contactLoan;
    }

    public void setContactLoan(String contactLoan) {
        this.contactLoan = contactLoan;
    }

    public String getContactCreditCard() {
        return contactCreditCard;
    }

    public void setContactCreditCard(String contactCreditCard) {
        this.contactCreditCard = contactCreditCard;
    }

    public String getContactBank() {
        return contactBank;
    }

    public void setContactBank(String contactBank) {
        this.contactBank = contactBank;
    }

    public String getPhoneCall() {
        return phoneCall;
    }

    public void setPhoneCall(String phoneCall) {
        this.phoneCall = phoneCall;
    }

    public String getContactEachOther() {
        return contactEachOther;
    }

    public void setContactEachOther(String contactEachOther) {
        this.contactEachOther = contactEachOther;
    }

    public String getRegularCircle() {
        return regularCircle;
    }

    public void setRegularCircle(String regularCircle) {
        this.regularCircle = regularCircle;
    }

    public Integer getContactsClass1BlacklistCnt() {
        return contactsClass1BlacklistCnt;
    }

    public void setContactsClass1BlacklistCnt(Integer contactsClass1BlacklistCnt) {
        this.contactsClass1BlacklistCnt = contactsClass1BlacklistCnt;
    }

    public Integer getContactsClass2BlacklistCnt() {
        return contactsClass2BlacklistCnt;
    }

    public void setContactsClass2BlacklistCnt(Integer contactsClass2BlacklistCnt) {
        this.contactsClass2BlacklistCnt = contactsClass2BlacklistCnt;
    }

    public String getPhoneSilent() {
        return phoneSilent;
    }

    public void setPhoneSilent(String phoneSilent) {
        this.phoneSilent = phoneSilent;
    }

    public JSONArray getLinkmanChecks() {
        return JSON.parseArray(linkmanChecks);
    }

    public void setLinkmanChecks(JSONArray linkmanChecks) {
        this.linkmanChecks = linkmanChecks.toJSONString();
    }

    public JSONArray getCallList() {
        return JSON.parseArray(callList);
    }

    public void setCallList(JSONArray callList) {
        this.callList = callList.toJSONString();
    }
}
