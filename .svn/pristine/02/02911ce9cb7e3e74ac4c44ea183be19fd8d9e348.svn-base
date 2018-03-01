package fengkongweishi.entity.personreport.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import fengkongweishi.enums.Color;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 身份验证
 *
 * @author huanghengkun
 * @date 2018/02/26
 */
@Entity
@Table(name = "po_id_check")
public class IdCheckPO extends BaseAnalyseItem {
    /**
     * 姓名手机号身份证三要素
     */
    private String nmPhCn;
    /**
     * 地址与身份证
     */
    private String adCn;
    /**
     * 银行卡与身份证
     */
    private String bcCn;

    public IdCheckPO() {
    }

    public IdCheckPO(Color color) {
        super(color);
    }

    public String getNmPhCn() {
        return nmPhCn;
    }

    public void setNmPhCn(String nmPhCn) {
        this.nmPhCn = nmPhCn;
    }

    public String getAdCn() {
        return adCn;
    }

    public void setAdCn(String adCn) {
        this.adCn = adCn;
    }

    public String getBcCn() {
        return bcCn;
    }

    public void setBcCn(String bcCn) {
        this.bcCn = bcCn;
    }
}
