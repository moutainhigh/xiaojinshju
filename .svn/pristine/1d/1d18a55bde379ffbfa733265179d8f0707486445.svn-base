package fengkongweishi.entity.personreport.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fengkongweishi.annotation.Info;

import java.util.Objects;

/**
 * 淘宝消费分析
 *
 * @author liuzhenfeng
 * @date 2018/1/31
 */
public class ConsumeAnsyc {

    @Info(label = "消费年月", tip = "", placeholder = "", help = "", secret = "")
    private String datetime;

    @Info(label = "消费金额", tip = "", placeholder = "", help = "", secret = "")
    private String consumeAmount;

    @Info(label = "消费次数", tip = "", placeholder = "", help = "", secret = "")
    private String consumeTime;

  


    @Override
    public String toString() {
        return "ConsumeAnsyc [datetime=" + datetime + ", consumeAmount=" + consumeAmount + ", consumeTime="
                + consumeTime + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsumeAnsyc that = (ConsumeAnsyc) o;
        return Objects.equals(datetime, that.datetime) &&
                Objects.equals(consumeAmount, that.consumeAmount) &&
                Objects.equals(consumeTime, that.consumeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datetime, consumeAmount, consumeTime);
    }

    public ConsumeAnsyc(String datetime, String consumeAmount, String consumeTime) {
        super();
        this.datetime = datetime;
        this.consumeAmount = consumeAmount;
        this.consumeTime = consumeTime;
       
    }


    public ConsumeAnsyc() {
        super();
    }


    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(String consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }


}
