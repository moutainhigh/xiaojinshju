package fengkongweishi.entity.personreport.vo;

import fengkongweishi.enums.Color;

import java.util.Objects;

/**
 * 分析评估项
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class RiskItem {
    private String content;
    private Color color;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public RiskItem(String content, Color color) {
        this.content = content;
        this.color = color;
    }

    public RiskItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RiskItem riskItem = (RiskItem) o;
        return Objects.equals(content, riskItem.content) &&
                color == riskItem.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, color);
    }
}
