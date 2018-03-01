package fengkongweishi.entity.personreport.vo;

import fengkongweishi.annotation.Info;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.entity.personreport.po.TaoBaoPO;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收货地址分析
 *
 * @author liuzhenfeng
 * @date 2018/1/31
 */
@Info(label = "收货地址VO", tip = "", placeholder = "", help = "", secret = "")
public class DeliverAddressVO implements IAnalyseItem {

    @Info(label = "收货地址数量", tip = "", placeholder = "", help = "", secret = "")
    private Integer count;

    @Info(label = "具体收货地址", tip = "", placeholder = "", help = "", secret = "")
    private List deliverAddresses;

    private Color color;

    @Override
    public String toString() {
        return "DeliverAddressVO{" +
                "count=" + count +
                ", deliverAddresses=" + deliverAddresses +
                '}';
    }

    public DeliverAddressVO(TaoBaoPO taoBaoPO) {
        if (taoBaoPO != null) {
            this.color = taoBaoPO.getColor();
            if (taoBaoPO.getDeliverAddress() != null) {
                this.deliverAddresses = taoBaoPO.getDeliverAddress().stream().collect(Collectors.toList());
                this.count = taoBaoPO.getDeliverAddress().size();
            } else {
                this.deliverAddresses = Collections.emptyList();
                this.count = 0;
            }
        }

    }

    public Integer getCount() {
        return count;
    }

    public List getDeliverAddresses() {
        return deliverAddresses;
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
