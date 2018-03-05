package fengkongweishi.entity.personreport.po;

import fengkongweishi.enums.Color;

import javax.persistence.*;
import java.util.Set;

/**
 * 法院裁决po对象
 *
 * @author huanghengkun
 * @date 2018/02/26
 */
@Entity
@Table(name = "po_court_judgment")
public class CourtJudgmentPO extends BaseAnalyseItem {
    /**
     * 具体法院裁决
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "belongTo")
    private Set<CourtJudgmentPOItem> courtJudgments;

    /**
     * 法院裁决风险分析数量
     */
    private Integer count;

    public CourtJudgmentPO() {
    }

    public CourtJudgmentPO(Color color) {
        super(color);
    }

    public Set<CourtJudgmentPOItem> getCourtJudgments() {
        return courtJudgments;
    }

    public void setCourtJudgments(Set<CourtJudgmentPOItem> courtJudgments) {
        this.courtJudgments = courtJudgments;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
