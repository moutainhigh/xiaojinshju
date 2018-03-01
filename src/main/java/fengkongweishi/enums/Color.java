package fengkongweishi.enums;

/**
 * 风险颜色枚举类.
 * 该类使用了compareTo方法,请不要随意变更各枚举项的顺序
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public enum Color {
    /**
     * 无,绿色  level 1
     */
    SUCCESS,
    /**
     * 注意,蓝紫色  level 2
     */
    ATTENTION,
    /**
     * 警告,橙色   level 3
     */
    WARNING,
    /**
     * 危险,红色   level 4
     */
    DANGER,
    /**
     * 错误,无数据等,灰色   level 5
     */
    ERROR,
    /**
     * 超时,灰色    level 6
     */
    TIMEOUT
}
