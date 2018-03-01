package fengkongweishi.entity.personreport.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import fengkongweishi.enums.Color;

/**
 * 失信黑名单po对象
 * 
 * @author Administrator
 * @date 2018/2/26
 */
@Entity
@Table(name = "po_dishonest_black")
public class DishonestBlackPO extends BaseAnalyseItem {
	/**
	 * 具体失信记录
	 */
	@Column(columnDefinition = "text")
	private String dishonestBlacks;
	
	/**
	 * 失信数量
	 */
	private Integer count;
	
	

	public DishonestBlackPO() {
		super();
	}
	
	public DishonestBlackPO(Color color) {
		super(color);
	}
	

	public JSONArray getDishonestBlacks() {
		return JSON.parseArray(dishonestBlacks);
	}

	public void setDishonestBlacks(JSONArray dishonestBlacks) {
		this.dishonestBlacks = JSON.toJSONString(dishonestBlacks);
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}
