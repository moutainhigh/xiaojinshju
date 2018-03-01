package fengkongweishi.enums;

/**
 * 客户评价等级
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public enum Level {
    /**
     * 优
     */
    GREAT("优"),
    /**
     * 良
     */
    GOOD("良"),
    /**
     * 差
     */
    BAD("差");

    private String message;

    Level(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
