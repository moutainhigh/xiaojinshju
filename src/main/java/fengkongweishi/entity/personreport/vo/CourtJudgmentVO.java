package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.CourtJudgmentPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 法院裁决
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class CourtJudgmentVO implements IAnalyseItem {
    /**
     * 具体法院裁决
     */
    private List<CourtJudgment> courJudgmentList;

    /**
     * 法院裁决风险分析数量
     */
    private Integer count;

    private Color color;

    @Override
    public String toString() {
        return "CourtJudgmentVO{" +
                "courJudgmentList=" + courJudgmentList +
                ", count=" + count +
                '}';
    }

    public CourtJudgmentVO(CourtJudgmentPO courtJudgmentPO) {
        if (courtJudgmentPO != null) {
            this.color = courtJudgmentPO.getColor();
            if (courtJudgmentPO.getCourtJudgments() != null) {
                this.courJudgmentList = new ArrayList<>();
                courtJudgmentPO.getCourtJudgments().stream().forEach(item -> this.courJudgmentList.add(new CourtJudgment(
                        item.getDocId(),
                        item.getTitle(),
                        item.getCaseType(),
                        item.getConcludeTime(),
                        item.getContent()
                )));
                this.count = courtJudgmentPO.getCourtJudgments().size();
            } else {
                this.courJudgmentList = Collections.emptyList();
                this.count = 0;
            }
        }
    }

    public List<CourtJudgment> getCourJudgmentList() {
        return courJudgmentList;
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
