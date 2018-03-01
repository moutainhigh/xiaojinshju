package com.alipay.api.response;

import java.util.List;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import com.alipay.api.domain.IntelligentPromoShopSummaryInfo;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: koubei.marketing.campaign.intelligent.shop.consult response.
 * 
 * @author auto create
 * @since 1.0, 2017-10-12 10:54:18
 */
public class KoubeiMarketingCampaignIntelligentShopConsultResponse extends AlipayResponse {

	private static final long serialVersionUID = 2347249789231439918L;

	/** 
	 * 智能营销方案符合标准的门店列表
	 */
	@ApiListField("shops")
	@ApiField("intelligent_promo_shop_summary_info")
	private List<IntelligentPromoShopSummaryInfo> shops;

	public void setShops(List<IntelligentPromoShopSummaryInfo> shops) {
		this.shops = shops;
	}
	public List<IntelligentPromoShopSummaryInfo> getShops( ) {
		return this.shops;
	}

}