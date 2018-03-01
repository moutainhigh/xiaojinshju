package fengkongweishi.entity.personreport.po;
/**
 * 车辆违章po对象
 * @author Administrator
 * @date 2018/2/26
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import fengkongweishi.enums.Color;

@Entity
@Table(name = "po_violation")
public class ViolationPO extends BaseAnalyseItem{
    /**
     * 具体违章记录
     */
    @Column(columnDefinition = "text")
    private String violations;
    
    /**
     * 违章数量
     */
    private Integer count;

    public ViolationPO() {
        super();
    }
    
    public ViolationPO(Color color) {
        super(color);
    }

    public JSONArray getViolations() {
        return JSON.parseArray(violations);
    }

    public void setViolations(JSONArray violations) {
        this.violations = JSON.toJSONString(violations);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
    
}
