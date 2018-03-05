package fengkongweishi.entity.personreport.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import fengkongweishi.enums.Color;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 淘宝PO对象
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "po_taobao")
public class TaoBaoPO extends BaseAnalyseItem {

    /**
     * 姓名
     */
    private String name;
    /**
     * 淘宝昵称
     */
    private String taobaoNickName;
    /**
     * 绑定手机号
     */
    private String mobile;
    /**
     * 绑定支付宝账号
     */
    private String alipayAccount;
    /**
     * 淘宝VIP等级
     */
    private String taobaoVIP;
    /**
     * 淘宝成长值
     */
    private String taobaoGrowth;
    /**
     * 支付宝余额
     */
    private String alipayBalance;
    /**
     * 余额宝余额
     */
    private String yuebaoBalance;
    /**
     * 花呗授信额度
     */
    private String antCheckLaterCreditLine;
    /**
     * 花呗可用额度
     */
    private String antCheckLaterAvailableCredit;

    /**
     * 消费记录
     */
    @Column(columnDefinition = "text")
    private String consumeOrder;
    /**
     * 消费分析
     */
    @Column(columnDefinition = "text")
    private String consumeAnsyc;

    /**
     * 收货地址
     */
    @Column(columnDefinition = "text")
    private String deliverAddress;
    
    

    @Override
    public String toString() {
        return "TaoBaoPO [name=" + name + ", taobaoNickName=" + taobaoNickName + ", mobile=" + mobile
                + ", alipayAccount=" + alipayAccount + ", taobaoVIP=" + taobaoVIP + ", taobaoGrowth=" + taobaoGrowth
                + ", alipayBalance=" + alipayBalance + ", yuebaoBalance=" + yuebaoBalance + ", antCheckLaterCreditLine="
                + antCheckLaterCreditLine + ", antCheckLaterAvailableCredit=" + antCheckLaterAvailableCredit
                + ", consumeOrder=" + consumeOrder + ", consumeAnsyc=" + consumeAnsyc + ", deliverAddress="
                + deliverAddress + "]";
    }

    public TaoBaoPO() {
        super();
    }

    public TaoBaoPO(Color color) {
        super(color);
    }

    public TaoBaoPO(String name, String mobile, String alipayAccount, String taobaoVIP, String taobaoGrowth,
            String alipayBalance, String yuebaoBalance, String antCheckLaterCreditLine,
            String antCheckLaterAvailableCredit) {
        super();
        this.name = name;
        this.mobile = mobile;
        this.alipayAccount = alipayAccount;
        this.taobaoVIP = taobaoVIP;
        this.taobaoGrowth = taobaoGrowth;
        this.alipayBalance = alipayBalance;
        this.yuebaoBalance = yuebaoBalance;
        this.antCheckLaterCreditLine = antCheckLaterCreditLine;
        this.antCheckLaterAvailableCredit = antCheckLaterAvailableCredit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaobaoNickName() {
        return taobaoNickName;
    }

    public void setTaobaoNickName(String taobaoNickName) {
        this.taobaoNickName = taobaoNickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getTaobaoVIP() {
        return taobaoVIP;
    }

    public void setTaobaoVIP(String taobaoVIP) {
        this.taobaoVIP = taobaoVIP;
    }

    public String getTaobaoGrowth() {
        return taobaoGrowth;
    }

    public void setTaobaoGrowth(String taobaoGrowth) {
        this.taobaoGrowth = taobaoGrowth;
    }

    public String getAlipayBalance() {
        return alipayBalance;
    }

    public void setAlipayBalance(String alipayBalance) {
        this.alipayBalance = alipayBalance;
    }

    public String getYuebaoBalance() {
        return yuebaoBalance;
    }

    public void setYuebaoBalance(String yuebaoBalance) {
        this.yuebaoBalance = yuebaoBalance;
    }

    public String getAntCheckLaterCreditLine() {
        return antCheckLaterCreditLine;
    }

    public void setAntCheckLaterCreditLine(String antCheckLaterCreditLine) {
        this.antCheckLaterCreditLine = antCheckLaterCreditLine;
    }

    public String getAntCheckLaterAvailableCredit() {
        return antCheckLaterAvailableCredit;
    }

    public void setAntCheckLaterAvailableCredit(String antCheckLaterAvailableCredit) {
        this.antCheckLaterAvailableCredit = antCheckLaterAvailableCredit;
    }

    public JSONArray getConsumeOrder() {
        return JSON.parseArray(consumeOrder);
    }

    public void setConsumeOrder(JSONArray consumeOrder) {
        this.consumeOrder = JSON.toJSONString(consumeOrder);
    }

    public JSONArray getConsumeAnsyc() {
        return JSON.parseArray(consumeAnsyc);
    }

    public void setConsumeAnsyc(JSONArray consumeAnsyc) {
        this.consumeAnsyc = JSON.toJSONString(consumeAnsyc);
    }

    public JSONArray getDeliverAddress() {
        return JSON.parseArray(deliverAddress);
    }

    public void setDeliverAddress(JSONArray deliverAddress) {
        this.deliverAddress = JSON.toJSONString(deliverAddress);
    }

}
