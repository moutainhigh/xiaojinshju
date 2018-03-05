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
     * 互通过的电话号码证据
     */
    private String contactEachOtherEvidence;
    /**
     * 朋友圈
     */
    private String regularCircle;
    /**
     * 朋友圈证据
     */
    private String regularCircleEvidence;
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
     * 关机情况证据
     */
    private String phoneSilentEvidence;

    private Color color;

    @Override
    public String toString() {
        return "SocialContactVO{" +
                "linkmanChecks=" + linkmanChecks +
                ", contactEachOther='" + contactEachOther + '\'' +
                ", contactEachOtherEvidence='" + contactEachOtherEvidence + '\'' +
                ", regularCircle='" + regularCircle + '\'' +
                ", regularCircleEvidence='" + regularCircleEvidence + '\'' +
                ", contactsClass1BlacklistCnt=" + contactsClass1BlacklistCnt +
                ", contactsClass2BlacklistCnt=" + contactsClass2BlacklistCnt +
                ", phoneSilent='" + phoneSilent + '\'' +
                ", phoneSilentEvidence='" + phoneSilentEvidence + '\'' +
                ", color=" + color +
                '}';
    }

    public SocialContactVO(CarrierPO carrierPO) {
        if (carrierPO != null) {
            this.color = carrierPO.getColor();
            this.contactEachOther = carrierPO.getContactEachOther();
            this.contactEachOtherEvidence = carrierPO.getContactEachOtherEvidence();
            this.regularCircle = carrierPO.getRegularCircle();
            this.regularCircleEvidence = carrierPO.getRegularCircleEvidence();
            this.contactsClass1BlacklistCnt = carrierPO.getContactsClass1BlacklistCnt();
            this.contactsClass2BlacklistCnt = carrierPO.getContactsClass2BlacklistCnt();
            this.phoneSilent = carrierPO.getPhoneSilent();
            this.phoneSilentEvidence = carrierPO.getPhoneSilentEvidence();
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

    public String getContactEachOtherEvidence() {
        return contactEachOtherEvidence;
    }

    public String getRegularCircleEvidence() {
        return regularCircleEvidence;
    }

    public String getPhoneSilentEvidence() {
        return phoneSilentEvidence;
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
