package fengkongweishi.entity.personreport.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import fengkongweishi.enums.Color;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 刑事案件po对象
 *
 * @author huanghengkun
 * @date 2018/02/26
 */
@Entity
@Table(name = "po_criminal")
public class CriminalPO extends BaseAnalyseItem {
    /**
     * 具体刑事犯罪
     */
    private String criminals;
    /**
     * 刑事犯罪数量
     */
    private Integer count;

    public CriminalPO() {
    }

    public CriminalPO(Color color) {
        super(color);
    }

    public JSONArray getCriminals() {
        return JSON.parseArray(criminals);
    }

    public void setCriminals(JSONArray criminals) {
        this.criminals = criminals.toJSONString();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
