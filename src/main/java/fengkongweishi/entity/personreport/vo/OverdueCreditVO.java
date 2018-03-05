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
 * 信贷逾期
 *
 * @author huanghengkun
 * @date 2018/01/31
 */
public class OverdueCreditVO implements IAnalyseItem {
    /**
     * 信贷逾期
     */
    private List<BlackRiskItem> overdueCredits;
    /**
     * 数量
     */
    private Integer count;

    private Color color;

    @Override
    public String toString() {
        return "OverdueCreditVO{" +
                "overdueCredits=" + overdueCredits +
                ", count=" + count +
                '}';
    }

    public OverdueCreditVO(BlackRiskPO blackRiskPO) {
        if (blackRiskPO != null) {
            this.color = blackRiskPO.getColor();
            if (blackRiskPO.getBlackRisks() != null) {
                JSONArray blackRisks = blackRiskPO.getBlackRisks();
                this.overdueCredits = blackRisks.stream().filter(OverdueCreditVO::filterOverdueCredit).map(OverdueCreditVO::map2BlackRiskItem).collect(Collectors.toList());
                this.count = this.overdueCredits.size();
            } else {
                this.count = 0;
                this.overdueCredits = Collections.emptyList();
            }
        }
    }

    private static boolean filterOverdueCredit(Object o) {
        JSONObject blackRiskItem = (JSONObject) o;
        return "B".equals(blackRiskItem.get("blackRiskType"));
    }

    private static BlackRiskItem map2BlackRiskItem(Object o) {
        JSONObject blackRiskItem = (JSONObject) o;
        return new BlackRiskItem(convertBlackFactsTypeB(blackRiskItem.getString("blackFactsType")), blackRiskItem.getString("blackFacts"));
    }

    private static String convertBlackFactsTypeB(String blackFactsType) {
        // B01：失联 B02：贷款不良（逾期90天以上未还） B03：短时逾期 B04：逾期
        switch (blackFactsType) {
            case "B01":
                return "失联";
            case "B02":
                return "贷款不良";
            case "B03":
                return "短时逾期";
            case "B04":
                return "逾期";
            default:
                return "";
        }
    }

    public List<BlackRiskItem> getOverdueCredits() {
        return overdueCredits;
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
