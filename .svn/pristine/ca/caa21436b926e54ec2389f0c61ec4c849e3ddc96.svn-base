package fengkongweishi.entity.personreport.vo;

import com.alibaba.fastjson.JSONObject;
import fengkongweishi.annotation.Info;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.entity.personreport.po.TaoBaoPO;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 淘宝消费VO
 *
 * @author liuzhenfeng
 * @date 2018/1/31
 */
@Info(label = "消费分析返回VO", tip = "", placeholder = "", help = "", secret = "")
public class ConsumeVO implements IAnalyseItem {

    @Info(label = "消费记录数", tip = "", placeholder = "", help = "", secret = "")
    private Integer consumeTime;

    @Info(label = "具体消费记录", tip = "", placeholder = "", help = "", secret = "")
    private List consumeOrders;

    @Info(label = "每月消费分析", tip = "", placeholder = "", help = "", secret = "")
    private List consumeAnsycs;

    private Color color;

    public ConsumeVO(TaoBaoPO taoBaoPO) {
        if (taoBaoPO != null) {
            this.color = taoBaoPO.getColor();
            if (taoBaoPO.getConsumeOrder() != null) {
                this.consumeTime = taoBaoPO.getConsumeOrder().size();
                this.consumeOrders = taoBaoPO.getConsumeOrder().stream()
                        .sorted((row1, row2) -> ((JSONObject) row2).getString("datetime").compareTo(((JSONObject) row1).getString("datetime")))
                        .collect(Collectors.toList());
            } else {
                this.consumeTime = 0;
                this.consumeOrders = Collections.emptyList();
            }
            if (taoBaoPO.getConsumeAnsyc() != null) {
                this.consumeAnsycs = taoBaoPO.getConsumeAnsyc().stream()
                        .sorted((row1, row2) -> ((JSONObject) row2).getString("datetime").compareTo(((JSONObject) row1).getString("datetime")))
                        .collect(Collectors.toList());
            } else {
                this.consumeAnsycs = Collections.emptyList();
            }
        }
    }

    @Override
    public String toString() {
        return "ConsumeVO [consumeTime=" + consumeTime + ", consumeOrders=" + consumeOrders + ", consumeAnsycs="
                + consumeAnsycs + "]";
    }

    public Integer getConsumeTime() {
        return consumeTime;
    }

    public List getConsumeOrders() {
        return consumeOrders;
    }

    public List getConsumeAnsycs() {
        return consumeAnsycs;
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
