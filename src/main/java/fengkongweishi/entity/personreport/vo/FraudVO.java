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
        return new BlackRiskItem(blackRiskItem.getString("blackFactsType"), blackRiskItem.getString("blackFacts"));
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
