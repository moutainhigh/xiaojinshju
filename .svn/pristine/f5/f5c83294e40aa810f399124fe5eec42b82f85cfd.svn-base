package fengkongweishi.entity.personreport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author duyiting
 * @date 2018/03/13
 */
public class ReportForSearch {

    private String no;//报告编号
    private String name;
    private String dateSelect;
    private Date beginTime;
    private Date endTime;
    private Integer createdByCompany;
    private Integer createdBy;
    private String level;



    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCreatedByCompany() {
        return createdByCompany;
    }

    public void setCreatedByCompany(Integer createByCompany) {
        this.createdByCompany = createByCompany;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNo() {

        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
