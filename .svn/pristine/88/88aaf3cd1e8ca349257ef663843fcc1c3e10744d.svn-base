package fengkongweishi.entity.personreport.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fengkongweishi.annotation.Info;

import java.util.Objects;

/**
 * 收货地址分析
 *
 * @author liuzhenfeng
 * @date 2018/1/31
 */

@Info(label = "收货地址分析", tip = "", placeholder = "", help = "", secret = "")
public class DeliverAddress {

    @Info(label = "收货人姓名", tip = "", placeholder = "", help = "", secret = "")
    private String name;

    @Info(label = "收货人联系电话", tip = "", placeholder = "", help = "", secret = "")
    private String phone;

    @Info(label = "邮政编码", tip = "", placeholder = "", help = "", secret = "")
    private String zipCode;

    @Info(label = "收货地址", tip = "", placeholder = "", help = "", secret = "")
    private String address;

    @Info(label = "是否是默认收货地址", tip = "", placeholder = "", help = "", secret = "")
    private String defaultAddress;

   


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeliverAddress that = (DeliverAddress) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(address, that.address) &&
                Objects.equals(defaultAddress, that.defaultAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, zipCode, address, defaultAddress);
    }

    public DeliverAddress(String name, String phone, String zipCode, String address, String defaultAddress) {
        super();
        this.name = name;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
        this.defaultAddress = defaultAddress;
    }

    public DeliverAddress() {
        super();
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }





}
