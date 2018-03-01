package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: zhima.customer.ep.certification.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-10-13 14:43:23
 */
public class ZhimaCustomerEpCertificationQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 8473322712873958986L;

	/** 
	 * 认证不通过的原因
	 */
	@ApiField("failed_reason")
	private String failedReason;

	/** 
	 * 认证是否通过，通过为true，不通过为false
	 */
	@ApiField("passed")
	private String passed;

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}
	public String getFailedReason( ) {
		return this.failedReason;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}
	public String getPassed( ) {
		return this.passed;
	}

}
