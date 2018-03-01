package fengkongweishi.entity.personreport.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import fengkongweishi.enums.Color;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 个人风险
 *
 * @author huanghengkun
 * @date 2018/02/26
 */
@Entity
@Table(name = "po_black_risk")
public class BlackRiskPO extends BaseAnalyseItem {
    @Column(columnDefinition = "text")
    private String blackRisks;

    public BlackRiskPO() {
    }

    public BlackRiskPO(Color color) {
        super(color);
    }

    public JSONArray getBlackRisks() {
        return JSON.parseArray(blackRisks);
    }

    public void setBlackRisks(JSONArray blackRisks) {
        this.blackRisks = blackRisks.toJSONString();
    }
}
