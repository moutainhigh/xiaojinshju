package fengkongweishi.entity.personreport.bo;

/**
 * 个人风险
 *
 * @author huanghengkun
 * @date 2018/02/26
 */
public class BlackRiskItem {
    /**
     * 黑名单风险类型，A-严重违法;B-信贷逾期;C-法院失信;D-潜在风 险;E-多头借贷
     */
    private String blackRiskType;
    /**
     * 黑名单事实类型。取值说明：
     * A02：名下公司税务重大违法
     * B01：失联
     * B02：贷款不良（逾期90天以上未还）
     * B03：短时逾期
     * B04：逾期
     * C01：失信被执行人
     * C02：被执行人
     * C03：裁判文书
     * D01：疑似催收风险
     * D02：名下公司存在违规行为（被税务局或者工商局公示）
     * D03：来自信贷高风险区域
     * D04：其他潜在风险
     * E01：7天内多头借贷
     * E02：1月内多头借贷
     * E03：3月内多头借贷
     * E04：疑似多头借贷
     */
    private String blackFactsType;
    /**
     * 黑名单事实
     */
    private String blackFacts;
    /**
     * 事件涉及金额
     */
    private String blackAmt;
    /**
     * 事件发生日期（格式：yyyy-MM-dd)
     */
    private String blackHappenDate;
    /**
     * 事件持续时长
     */
    private String blackDurationTime;
    /**
     * 信息来源
     */
    private String blackPublishSource;

    public BlackRiskItem() {
    }

    public BlackRiskItem(String blackRiskType, String blackFactsType, String blackFacts, String blackAmt, String blackHappenDate, String blackDurationTime, String blackPublishSource) {
        this.blackRiskType = blackRiskType;
        this.blackFactsType = blackFactsType;
        this.blackFacts = blackFacts;
        this.blackAmt = blackAmt;
        this.blackHappenDate = blackHappenDate;
        this.blackDurationTime = blackDurationTime;
        this.blackPublishSource = blackPublishSource;
    }

    public String getBlackRiskType() {
        return blackRiskType;
    }

    public void setBlackRiskType(String blackRiskType) {
        this.blackRiskType = blackRiskType;
    }

    public String getBlackFactsType() {
        return blackFactsType;
    }

    public void setBlackFactsType(String blackFactsType) {
        this.blackFactsType = blackFactsType;
    }

    public String getBlackFacts() {
        return blackFacts;
    }

    public void setBlackFacts(String blackFacts) {
        this.blackFacts = blackFacts;
    }

    public String getBlackAmt() {
        return blackAmt;
    }

    public void setBlackAmt(String blackAmt) {
        this.blackAmt = blackAmt;
    }

    public String getBlackHappenDate() {
        return blackHappenDate;
    }

    public void setBlackHappenDate(String blackHappenDate) {
        this.blackHappenDate = blackHappenDate;
    }

    public String getBlackDurationTime() {
        return blackDurationTime;
    }

    public void setBlackDurationTime(String blackDurationTime) {
        this.blackDurationTime = blackDurationTime;
    }

    public String getBlackPublishSource() {
        return blackPublishSource;
    }

    public void setBlackPublishSource(String blackPublishSource) {
        this.blackPublishSource = blackPublishSource;
    }
}
