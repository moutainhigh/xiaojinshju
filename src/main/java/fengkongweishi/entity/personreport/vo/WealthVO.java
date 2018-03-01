package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.entity.personreport.po.TaoBaoPO;
import fengkongweishi.enums.Color;

/**
 * 财富信息
 *
 * @author huanghengkun
 * @date 2018/01/31
 */
public class WealthVO implements IAnalyseItem {
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

    private Color color;

    @Override
    public String toString() {
        return "WealthVO{" +
                "name='" + name + '\'' +
                ", taobaoNickName='" + taobaoNickName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", alipayAccount='" + alipayAccount + '\'' +
                ", taobaoVIP='" + taobaoVIP + '\'' +
                ", taobaoGrowth=" + taobaoGrowth +
                ", alipayBalance=" + alipayBalance +
                ", yuebaoBalance=" + yuebaoBalance +
                ", antCheckLaterCreditLine=" + antCheckLaterCreditLine +
                ", antCheckLaterAvailableCredit=" + antCheckLaterAvailableCredit +
                '}';
    }

    public WealthVO(TaoBaoPO taoBaoPO) {
        if (taoBaoPO != null) {
            this.color = taoBaoPO.getColor();
            this.name = taoBaoPO.getName();
            this.taobaoNickName = taoBaoPO.getTaobaoNickName();
            this.mobile = taoBaoPO.getMobile();
            this.alipayAccount = taoBaoPO.getAlipayAccount();
            this.taobaoVIP = taoBaoPO.getTaobaoVIP();
            this.taobaoGrowth = taoBaoPO.getTaobaoGrowth();
            this.alipayBalance = taoBaoPO.getAlipayBalance();
            this.yuebaoBalance = taoBaoPO.getYuebaoBalance();
            this.antCheckLaterCreditLine = taoBaoPO.getAntCheckLaterCreditLine();
            this.antCheckLaterAvailableCredit = taoBaoPO.getAntCheckLaterAvailableCredit();
        }
    }

    public String getName() {
        return name;
    }

    public String getTaobaoNickName() {
        return taobaoNickName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public String getTaobaoVIP() {
        return taobaoVIP;
    }

    public String getTaobaoGrowth() {
        return taobaoGrowth;
    }

    public String getAlipayBalance() {
        return alipayBalance;
    }

    public String getYuebaoBalance() {
        return yuebaoBalance;
    }

    public String getAntCheckLaterCreditLine() {
        return antCheckLaterCreditLine;
    }

    public String getAntCheckLaterAvailableCredit() {
        return antCheckLaterAvailableCredit;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
