package fengkongweishi.entity.personreport.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fengkongweishi.annotation.Info;

import java.util.Objects;

/**
 * 具体消费订单
 *
 * @author liuzhenfeng
 * @date 2018/1/31
 */
@Info(label = "具体消费订单", tip = "", placeholder = "", help = "", secret = "")
public class ConsumeOrder {

    @Info(label = "消费时间", tip = "", placeholder = "", help = "", secret = "")
    private String datetime;

    @Info(label = "消费事件", tip = "", placeholder = "", help = "", secret = "")
    private String event;

    @Info(label = "消费金额", tip = "", placeholder = "", help = "", secret = "")
    private String money;

    

    @Override
    public String toString() {
        return "ConsumeOrder [datetime=" + datetime + ", event=" + event + ", money=" + money + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsumeOrder that = (ConsumeOrder) o;
        return Objects.equals(datetime, that.datetime) &&
                Objects.equals(event, that.event) &&
                Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datetime, event, money);
    }

   

    public ConsumeOrder(String datetime, String event, String money) {
        super();
        this.datetime = datetime;
        this.event = event;
        this.money = money;
    }

    public ConsumeOrder() {
        super();
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


}
