package fengkongweishi.enums;

/**
 * 支付渠道枚举类
 *
 * @author huanghengkun
 * @date 2018/01/08
 */
public enum PaymentChannelEnum {
    /**
     * 管理员充值
     */
    BACKEND("管理员充值"),
    /**
     * 前台充值
     */
    FRONTEND("前台充值"),
    /**
     * 基础版
     */
    PERSONJUNIOR("基础版"),
    /**
     * 通信版
     */
    PERSONMOBILE("通信版"),
    /**
     * 淘宝版
     */
    PERSONECOMMERCE("淘宝版"),
    /**
     * 全面版
     */
    PERSONSENIOR("全面版");

    private String message;

    PaymentChannelEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
