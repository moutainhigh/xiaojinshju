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
        return "D".equals(blackRiskItem.get("blackRiskType"));
    }

    private static BlackRiskItem map2BlackRiskItem(Object o) {
        JSONObject blackRiskItem = (JSONObject) o;
        return new BlackRiskItem(blackRiskItem.getString("blackFactsType"), blackRiskItem.getString("blackFacts"));
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
