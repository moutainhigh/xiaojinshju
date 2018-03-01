package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.entity.personreport.po.IdCardPO;
import fengkongweishi.enums.Color;

/**
 * 身份信息
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class IdCardVO implements IAnalyseItem {
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 出身地省
     */
    private String province;
    /**
     * 出身地市
     */
    private String city;
    /**
     * 出身地县区
     */
    private String town;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 运营商
     */
    private String mobileCompany;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 银行
     */
    private String bankName;
    /**
     * 地址
     */
    private String address;

    private Color color;

    public IdCardVO(IdCardPO idCardPO) {
        if (idCardPO != null) {
            this.color = idCardPO.getColor();
            this.name = idCardPO.getName();
            this.sex = idCardPO.getSex();
            this.age = idCardPO.getAge();
            this.province = idCardPO.getProvince();
            this.city = idCardPO.getCity();
            this.town = idCardPO.getTown();
            this.idCard = idCardPO.getIdCard();
            this.mobileCompany = idCardPO.getAddress();
            this.mobile = idCardPO.getAddress();
            this.bankCard = idCardPO.getIdCard();
            this.bankName = idCardPO.getName();
            this.address = idCardPO.getAddress();
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "IdCardVO{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", idCard='" + idCard + '\'' +
                ", mobileCompany='" + mobileCompany + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getTown() {
        return town;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getMobileCompany() {
        return mobileCompany;
    }

    public String getMobile() {
        return mobile;
    }

    public String getBankCard() {
        return bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAddress() {
        return address;
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
