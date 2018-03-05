package fengkongweishi.entity.customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huanghengkun
 * @date 2018/01/23
 */
public class CustomerForSearch {
    private Integer createBy;
    private String level;
    private String detail;
    private Date beginTime;
    private Date endTime;
    /**
     * 选择的时间
     * eg.2018-01-30 - 2018-03-13
     */
    private String dateSelect;

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDateSelect() {
        return dateSelect;
    }

    public void setDateSelect(String dateSelect) {
        this.dateSelect = dateSelect;
        if (this.dateSelect != null) {
            String[] dates = this.dateSelect.split(" - ");
            if (dates.length == 2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    this.beginTime = sdf.parse(dates[0]);
                    this.endTime = sdf.parse(dates[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
