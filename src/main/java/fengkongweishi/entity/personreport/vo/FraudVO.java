package fengkongweishi.entity.personreport.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fengkongweishi.entity.personreport.po.BlackRiskPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 潜在风险,欺诈分析
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class FraudVO implements IAnalyseItem {
    /**
     * 潜在风险
     */
    private List<BlackRiskItem> frauds;
    /**
     * 数量
     */
    private Integer count;

    private Color color;

    @Override
    public String toString() {
        return "FraudVO{" +
                "frauds=" + frauds +
                ", count=" + count +
                '}';
    }

    public FraudVO(BlackRiskPO blackRiskPO) {
        if (blackRiskPO != null) {
            this.color = blackRiskPO.getColor();
            if (blackRiskPO.getBlackRisks() != null) {
                JSONArray blackRisks = blackRiskPO.getBlackRisks();
                this.frauds = blackRisks.stream().filter(FraudVO::filterFraud).map(FraudVO::map2BlackRiskItem).collect(Collectors.toList());
                this.count = this.frauds.size();
            } else {
                this.count = 0;
                this.frauds = Collections.emptyList();
            }
        }
    }

    private static boolean filterFraud(Object o) {
        JSONObject blackRiskItem = (JSONObject) o;
        return "D".equals(blackRiskItem.get("blackRiskType"));
    }

    private static BlackRiskItem map2BlackRiskItem(Object o) {
        JSONObject blackRiskItem = (JSONObject) o;
        return new BlackRiskItem(convertBlackFactsTypeD(blackRiskItem.getString("blackFactsType")), blackRiskItem.getString("blackFacts"));
    }

    private static String convertBlackFactsTypeD(String blackFactsType) {
        // D01：疑似催收风险 D02：名下公司存在违规行为（被税务局或者工商局公示） D03：来自信贷高风险区域 D04：其他潜在风险
        switch (blackFactsType) {
            case "D01":
                return "疑似催收风险";
            case "D02":
                return "名下公司存在违规行为(被税务局或者工商局公示)";
            case "D03":
                return "来自信贷高风险区域";
            case "D04":
                return "其他潜在风险";
            default:
                return "";
        }
    }

    public List<BlackRiskItem> getFrauds() {
        return frauds;
    }

    public Integer getCount() {
        return count;
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
