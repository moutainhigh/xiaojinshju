package fengkongweishi.entity.evaluate;

import com.fasterxml.jackson.annotation.JsonFormat;
import fengkongweishi.annotation.Info;
import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.customer.Customer;
import fengkongweishi.entity.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author liuzhenfeng
 * @date 2018/1/31
 */
@Entity
@Info(label = "被查询人备注记录", tip = "", placeholder = "", help = "", secret = "")
public class Evaluation extends BaseEntity {

    @Info(label = "备注详情", tip = "", placeholder = "", help = "", secret = "")
    private String comment;

    @Info(label = "评论时间", tip = "", placeholder = "", help = "", secret = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date evaluateDate;

    @ManyToOne
    @JoinColumn(name = "by_user_id")
    @Info(label = "评论人", tip = "", placeholder = "", help = "", secret = "")
    private User byUser;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_customer_id")
    @Info(label = "哪个客户的备注", tip = "", placeholder = "", help = "", secret = "")
    private Customer toCustomer;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Evaluation that = (Evaluation) o;
        return Objects.equals(comment, that.comment) &&
                Objects.equals(evaluateDate, that.evaluateDate) &&
                Objects.equals(byUser, that.byUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, evaluateDate, byUser);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public fengkongweishi.entity.user.User getByUser() {
        return byUser;
    }

    public void setByUser(fengkongweishi.entity.user.User user) {
        this.byUser = user;
    }

    public Customer getToCustomer() {
        return toCustomer;
    }

    public void setToCustomer(Customer toCustomer) {
        this.toCustomer = toCustomer;
    }


}
