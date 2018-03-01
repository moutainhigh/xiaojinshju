package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.DishonestBlackPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 失信记录
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class DishonestBlackVO implements IAnalyseItem {
    /**
     * 具体失信记录
     */
    private List dishonestBlackList;
    /**
     * 失信记录数量
     */
    private Integer count;

    private Color color;

    @Override
    public String toString() {
        return "DishonestBlackVO{" +
                "dishonestBlackList=" + dishonestBlackList +
                ", count=" + count +
                '}';
    }

    public DishonestBlackVO(DishonestBlackPO dishonestBlackPO) {
        if (dishonestBlackPO != null) {
            this.color = dishonestBlackPO.getColor();
            if (dishonestBlackPO.getDishonestBlacks() != null) {
                this.dishonestBlackList = dishonestBlackPO.getDishonestBlacks().stream().collect(Collectors.toList());
                this.count = dishonestBlackPO.getDishonestBlacks().size();
            } else {
                this.count = 0;
                this.dishonestBlackList = Collections.emptyList();
            }
        }
    }

    public List getDishonestBlackList() {
        return dishonestBlackList;
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
