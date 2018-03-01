package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.CarrierPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 社会关系
 * @author huanghengkun
 * @date 2018/01/31
 */
public class SocialContactVO implements IAnalyseItem {
    /**
     * 联系人分析
     */
    private List linkmanChecks;
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

    private Color color;

    @Override
    public String toString() {
        return "SocialContactVO{" +
                "linkmanChecks=" + linkmanChecks +
                ", contactEachOther='" + contactEachOther + '\'' +
                ", regularCircle='" + regularCircle + '\'' +
                ", contactsClass1BlacklistCnt=" + contactsClass1BlacklistCnt +
                ", contactsClass2BlacklistCnt=" + contactsClass2BlacklistCnt +
                ", phoneSilent='" + phoneSilent + '\'' +
                '}';
    }

    public SocialContactVO(CarrierPO carrierPO) {
        if (carrierPO != null) {
            this.color = carrierPO.getColor();
            this.contactEachOther = carrierPO.getContactEachOther();
            this.regularCircle = carrierPO.getRegularCircle();
            this.contactsClass1BlacklistCnt = carrierPO.getContactsClass1BlacklistCnt();
            this.contactsClass2BlacklistCnt = carrierPO.getContactsClass2BlacklistCnt();
            this.phoneSilent = carrierPO.getPhoneSilent();
            if (carrierPO.getLinkmanChecks() != null) {
                this.linkmanChecks = carrierPO.getLinkmanChecks().stream().collect(Collectors.toList());
            } else {
                this.linkmanChecks = Collections.emptyList();
            }
        }
    }

    public List getLinkmanChecks() {
        return linkmanChecks;
    }

    public String getContactEachOther() {
        return contactEachOther;
    }

    public String getRegularCircle() {
        return regularCircle;
    }

    public Integer getContactsClass1BlacklistCnt() {
        return contactsClass1BlacklistCnt;
    }

    public Integer getContactsClass2BlacklistCnt() {
        return contactsClass2BlacklistCnt;
    }

    public String getPhoneSilent() {
        return phoneSilent;
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
