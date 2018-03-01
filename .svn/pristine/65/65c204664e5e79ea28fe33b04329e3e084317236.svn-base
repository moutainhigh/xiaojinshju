package fengkongweishi.entity.personreport.bo;

import java.util.Objects;

/**
 * 通信数据联系人分析结果
 *
 * @author huanghengkun
 * @date 2018/02/26
 */
public class LinkmanCheck {
    /**
     * 关系
     */
    private String relationship;
    /**
     * 手机号码
     */
    private String cellPhone;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 与该联系人通话记录
     */
    private String checkMobile;
    /**
     * 联系号码是否是小号
     */
    private String checkXiaohao;

    public LinkmanCheck() {
    }

    public LinkmanCheck(String relationship, String cellPhone, String contactName, String checkMobile, String checkXiaohao) {
        this.relationship = relationship;
        this.cellPhone = cellPhone;
        this.contactName = contactName;
        this.checkMobile = checkMobile;
        this.checkXiaohao = checkXiaohao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LinkmanCheck that = (LinkmanCheck) o;
        return Objects.equals(relationship, that.relationship) &&
                Objects.equals(cellPhone, that.cellPhone) &&
                Objects.equals(contactName, that.contactName) &&
                Objects.equals(checkMobile, that.checkMobile) &&
                Objects.equals(checkXiaohao, that.checkXiaohao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relationship, cellPhone, contactName, checkMobile, checkXiaohao);
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCheckMobile() {
        return checkMobile;
    }

    public void setCheckMobile(String checkMobile) {
        this.checkMobile = checkMobile;
    }

    public String getCheckXiaohao() {
        return checkXiaohao;
    }

    public void setCheckXiaohao(String checkXiaohao) {
        this.checkXiaohao = checkXiaohao;
    }
}
