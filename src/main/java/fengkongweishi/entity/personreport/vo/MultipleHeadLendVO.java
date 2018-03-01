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
 * 多头借贷
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class MultipleHeadLendVO implements IAnalyseItem {
    /**
     * 多头信息
     */
    private List<BlackRiskItem> multipleHeadLends;
    /**
     * 数量
     */
    private Integer count;

    private Color color;

    @Override
    public String toString() {
        return "MultipleHeadLendVO{" +
                "multipleHeadLends=" + multipleHeadLends +
                ", count=" + count +
                '}';
    }

    public MultipleHeadLendVO(BlackRiskPO blackRiskPO) {
        if (blackRiskPO != null) {
            this.color = blackRiskPO.getColor();
            if (blackRiskPO.getBlackRisks() != null) {
                JSONArray blackRisks = blackRiskPO.getBlackRisks();
                this.multipleHeadLends = blackRisks.stream().filter(MultipleHeadLendVO::filterMultipleHeadLend).map(MultipleHeadLendVO::map2BlackRiskItem).collect(Collectors.toList());
                this.count = this.multipleHeadLends.size();
            } else {
                this.count = 0;
                this.multipleHeadLends = Collections.emptyList();
            }
        }
    }

    private static boolean filterMultipleHeadLend(Object o) {
        JSONObject blackRiskItem = (JSONObject) o;
        return "E".equals(blackRiskItem.get("blackRiskType"));
    }

    private static BlackRiskItem map2BlackRiskItem(Object o) {
        JSONObject blackRiskItem = (JSONObject) o;
        return new BlackRiskItem(blackRiskItem.getString("blackFactsType"), blackRiskItem.getString("blackFacts"));
    }

    public List<BlackRiskItem> getMultipleHeadLends() {
        return multipleHeadLends;
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
