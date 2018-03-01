package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.entity.personreport.po.ZhimaScorePO;
import fengkongweishi.enums.Color;

/**
 * 芝麻信用
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class ZhimaScoreVO implements IAnalyseItem {
    /**
     * 芝麻分
     */
    private String score;

    private Color color;

    @Override
    public String toString() {
        return super.toString() +
                "ZhimaScoreVO{" +
                "score='" + score + '\'' +
                '}';
    }

    public ZhimaScoreVO(ZhimaScorePO zhimaScorePO) {
        if (zhimaScorePO != null) {
            this.color = zhimaScorePO.getColor();
            this.score = zhimaScorePO.getScore();
        }
    }

    public String getScore() {
        return score;
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
