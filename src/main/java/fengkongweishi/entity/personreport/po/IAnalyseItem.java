package fengkongweishi.entity.personreport.po;

import fengkongweishi.enums.Color;

/**
 * 风险判断项
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public interface IAnalyseItem {
    /**
     * 获取风险颜色
     *
     * @return 风险颜色
     */
    Color getColor();

    /**
     * 设置风险颜色
     *
     * @param color 风险颜色
     */
    void setColor(Color color);
}
