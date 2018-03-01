package fengkongweishi.entity.personreport.po;

import fengkongweishi.enums.Color;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 银行卡po对象
 * 
 * @author Administrator
 * @date 2018/2/26
 */
@Entity
@Table(name = "po_bank")
public class BankPO extends BaseAnalyseItem {

    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 银行
     */
    private String bankName;

    public BankPO() {
        super();
    }

    public BankPO(Color color) {
        super(color);
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
