package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.entity.personreport.po.IdCheckPO;
import fengkongweishi.enums.Color;

/**
 * 身份验证
 *
 * @author huanghengkun
 * @date 2018/01/31
 */
public class IdCheckVO implements IAnalyseItem {
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

    private Color color;

    @Override
    public String toString() {
        return "IdCheckVO{" +
                "nmPhCn='" + nmPhCn + '\'' +
                ", adCn='" + adCn + '\'' +
                ", bcCn='" + bcCn + '\'' +
                '}';
    }

    public IdCheckVO(IdCheckPO idCheckPO) {
        if (idCheckPO != null) {
            this.color = idCheckPO.getColor();
            this.nmPhCn = idCheckPO.getNmPhCn();
            this.adCn = idCheckPO.getAdCn();
            this.bcCn = idCheckPO.getBcCn();
        }
    }

    public String getNmPhCn() {
        return nmPhCn;
    }

    public String getAdCn() {
        return adCn;
    }

    public String getBcCn() {
        return bcCn;
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
