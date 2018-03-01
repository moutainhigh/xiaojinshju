package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.CriminalPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 刑事犯罪
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class CriminalVO implements IAnalyseItem {

    /**
     * 具体刑事犯罪
     */
    private List criminalList;
    /**
     * 刑事犯罪数量
     */
    private Integer count;

    private Color color;

    @Override
    public String toString() {
        return "CriminalVO{" +
                "criminalList=" + criminalList +
                ", count=" + count +
                '}';
    }

    public CriminalVO(CriminalPO criminalPO) {
        if (criminalPO != null) {
            this.color = criminalPO.getColor();
            if (criminalPO.getCriminals() != null) {
                this.criminalList = criminalPO.getCriminals().stream().collect(Collectors.toList());
                this.count = criminalPO.getCriminals().size();
            } else {
                this.count = 0;
                this.criminalList = Collections.emptyList();
            }
        }
    }

    public List getCriminalList() {
        return criminalList;
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
