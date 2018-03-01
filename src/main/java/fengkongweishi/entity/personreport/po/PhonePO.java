package fengkongweishi.entity.personreport.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import fengkongweishi.enums.Color;

/**
 * 手机号归属地PO对象
 * 
 * @author Administrator
 * @date 2018/2/26
 */
@Entity
@Table(name = "po_phone")
public class PhonePO extends BaseAnalyseItem {

	/**
	 * 运营商
	 */
	private String mobileCompany;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 地址
	 */
	private String address;

	public PhonePO() {
		super();
	}

	public PhonePO(Color color) {
		super(color);
	}

	public String getMobileCompany() {
		return mobileCompany;
	}

	public void setMobileCompany(String mobileCompany) {
		this.mobileCompany = mobileCompany;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
