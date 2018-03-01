package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.CarrierPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

/**
 * 风险统计
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class CallRiskVO implements IAnalyseItem {
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

    private Color color;

    @Override
    public String toString() {
        return "CallRiskVO{" +
                "searchedOrganizationCount=" + searchedOrganizationCount +
                ", searchedOrganizationType='" + searchedOrganizationType + '\'' +
                ", contactCollection='" + contactCollection + '\'' +
                ", contactLoan='" + contactLoan + '\'' +
                ", contactCreditCard='" + contactCreditCard + '\'' +
                ", contactBank='" + contactBank + '\'' +
                ", phoneCall='" + phoneCall + '\'' +
                '}';
    }

    public CallRiskVO(CarrierPO carrierPO) {
        if (carrierPO != null) {
            this.color = carrierPO.getColor();
            this.searchedOrganizationCount = carrierPO.getSearchedOrganizationCount();
            this.searchedOrganizationType = carrierPO.getSearchedOrganizationType();
            this.contactCollection = carrierPO.getContactCollection();
            this.contactLoan = carrierPO.getContactLoan();
            this.contactCreditCard = carrierPO.getContactCreditCard();
            this.contactBank = carrierPO.getContactBank();
            this.phoneCall = carrierPO.getPhoneCall();
        }
    }

    public Integer getSearchedOrganizationCount() {
        return searchedOrganizationCount;
    }

    public String getSearchedOrganizationType() {
        return searchedOrganizationType;
    }

    public String getContactCollection() {
        return contactCollection;
    }

    public String getContactLoan() {
        return contactLoan;
    }

    public String getContactCreditCard() {
        return contactCreditCard;
    }

    public String getContactBank() {
        return contactBank;
    }

    public String getPhoneCall() {
        return phoneCall;
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
