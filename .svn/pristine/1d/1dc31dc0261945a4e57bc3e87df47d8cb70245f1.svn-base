package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.CarrierPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     * 催收类号码证据
     */
    private List contactCollectionEvidences;
    /**
     * 贷款类号码
     */
    private String contactLoan;
    /**
     * 贷款类号码证据
     */
    private List contactLoanEvidences;
    /**
     * 信用卡类
     */
    private String contactCreditCard;
    /**
     * 信用卡类证据
     */
    private List contactCreditCardEvidences;
    /**
     * 银行类
     */
    private String contactBank;
    /**
     * 银行类证据
     */
    private List contactBankEvidences;
    /**
     * 通话概况
     */
    private String phoneCall;
    /**
     * 通话概况证据
     */
    private List phoneCallEvidences;

    private Color color;

    @Override
    public String toString() {
        return "CallRiskVO{" +
                "searchedOrganizationCount=" + searchedOrganizationCount +
                ", searchedOrganizationType='" + searchedOrganizationType + '\'' +
                ", contactCollection='" + contactCollection + '\'' +
                ", contactCollectionEvidences=" + contactCollectionEvidences +
                ", contactLoan='" + contactLoan + '\'' +
                ", contactLoanEvidences=" + contactLoanEvidences +
                ", contactCreditCard='" + contactCreditCard + '\'' +
                ", contactCreditCardEvidences=" + contactCreditCardEvidences +
                ", contactBank='" + contactBank + '\'' +
                ", contactBankEvidences=" + contactBankEvidences +
                ", phoneCall='" + phoneCall + '\'' +
                ", phoneCallEvidences=" + phoneCallEvidences +
                ", color=" + color +
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
            if (carrierPO.getContactCollectionEvidences() != null) {
                this.contactCollectionEvidences = carrierPO.getContactCollectionEvidences().stream().collect(Collectors.toList());
            } else {
                this.contactCollectionEvidences = Collections.emptyList();
            }
            if (carrierPO.getContactLoanEvidences() != null) {
                this.contactLoanEvidences = carrierPO.getContactLoanEvidences().stream().collect(Collectors.toList());
            } else {
                this.contactLoanEvidences = Collections.emptyList();
            }
            if (carrierPO.getContactCreditCardEvidences() != null) {
                this.contactCreditCardEvidences = carrierPO.getContactCreditCardEvidences().stream().collect(Collectors.toList());
            } else {
                this.contactCreditCardEvidences = Collections.emptyList();
            }
            if (carrierPO.getContactBankEvidences() != null) {
                this.contactBankEvidences = carrierPO.getContactBankEvidences().stream().collect(Collectors.toList());
            } else {
                this.contactBankEvidences = Collections.emptyList();
            }
            if (carrierPO.getPhoneCallEvidences() != null) {
                this.phoneCallEvidences = carrierPO.getPhoneCallEvidences().stream().collect(Collectors.toList());
            } else {
                this.phoneCallEvidences = Collections.emptyList();
            }
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

    public List getContactCollectionEvidences() {
        return contactCollectionEvidences;
    }

    public List getContactLoanEvidences() {
        return contactLoanEvidences;
    }

    public List getContactCreditCardEvidences() {
        return contactCreditCardEvidences;
    }

    public List getContactBankEvidences() {
        return contactBankEvidences;
    }

    public List getPhoneCallEvidences() {
        return phoneCallEvidences;
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
