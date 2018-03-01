package fengkongweishi.entity.personreport.vo;

import fengkongweishi.annotation.Info;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.entity.personreport.po.ViolationPO;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 车辆违章
 *
 * @author liuzhenfeng
 * @date 2018/1/29
 */
@Info(label = "车辆违章VO", tip = "", placeholder = "", help = "", secret = "")
public class ViolationVO implements IAnalyseItem {

    @Info(label = "本次查询违章统计条数", tip = "", placeholder = "", help = "", secret = "")
    private Integer count;

    @Info(label = "具体违章记录", tip = "", placeholder = "", help = "", secret = "")
    private List violationSet;

    private Color color;

    @Override
    public String toString() {
        return "ViolationVO{" +
                "count=" + count +
                ", violationSet=" + violationSet +
                '}';
    }

    public ViolationVO(ViolationPO violationPO) {
        if (violationPO != null) {
            this.color = violationPO.getColor();
            if (violationPO.getViolations() != null) {
                this.count = violationPO.getViolations().size();
                this.violationSet = violationPO.getViolations().stream().collect(Collectors.toList());
            } else {
                this.count = 0;
                this.violationSet = Collections.emptyList();
            }
        }
    }

    public Integer getCount() {
        return count;
    }

    public List getViolationSet() {
        return violationSet;
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
