package fengkongweishi.enums;

/**
 * 查询状态枚举类
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public enum SearchStatusEnum {
    /**
     * 处理中
     */
    PROCESSING("处理中"),
    /**
     * 处理完成
     */
    SUCCESS("处理完成"),
    /**
     * 部分失败
     */
    ERROR("部分失败");

    private String message;

    SearchStatusEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
