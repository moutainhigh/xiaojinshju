package fengkongweishi.entity.personreport.vo;

/**
 * @author huanghengkun
 * @date 2018/02/26
 */
public class BlackRiskItem {
    /**
     * 事件类型
     */
    private String type;
    /**
     * 事件内容
     */
    private String content;

    @Override
    public String toString() {
        return "BlackRiskItem{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public BlackRiskItem(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
