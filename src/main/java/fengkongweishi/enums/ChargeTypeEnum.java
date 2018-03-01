package fengkongweishi.enums;

/**
 * @author huanghengkun
 * @date 2018/01/08
 */
public enum ChargeTypeEnum {
    /**
     * 充值
     */
    DEPOSIT("充值"),
    /**
     * 个人消费
     */
    WITHDRAW("个人消费"),
    /**
     * 公司消费
     */
    CONSUME("公司消费");

    private String message;

    ChargeTypeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
