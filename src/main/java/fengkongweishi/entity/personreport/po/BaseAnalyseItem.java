package fengkongweishi.entity.personreport.po;

import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.enums.Color;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * @author huanghengkun
 * @date 2018/01/09
 */
@MappedSuperclass
public abstract class BaseAnalyseItem extends BaseEntity implements IAnalyseItem {

    @Enumerated(EnumType.STRING)
    protected Color color;

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        if (isRiskHigher(color)) {
            this.color = color;
        }
    }

    private boolean isRiskHigher(Color newColor) {
        return this.color == null || this.color.compareTo(newColor) < 0;
    }

    @Override
    public String toString() {
        return "BaseAnalyseItem{" +
                "color=" + color +
                '}';
    }

    public BaseAnalyseItem() {
    }

    public BaseAnalyseItem(Color color) {
        this.color = color;
    }
}
