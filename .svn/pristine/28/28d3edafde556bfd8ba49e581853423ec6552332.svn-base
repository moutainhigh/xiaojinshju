package fengkongweishi.entity.personreport.vo;

import fengkongweishi.entity.personreport.po.EducationPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

/**
 * 学历信息
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class EducationVO implements IAnalyseItem {
    /**
     * 姓名
     */
    private String name;
    /**
     * 毕业结论
     */
    private String studyResult;
    /**
     * 毕业时间
     */
    private String graduateTime;
    /**
     * 学校
     */
    private String college;
    /**
     * 学历
     */
    private String degree;
    /**
     * 院校类型
     */
    private String collegeType;

    private Color color;

    @Override
    public String toString() {
        return super.toString() +
                "EducationVO{" +
                "name='" + name + '\'' +
                ", studyResult='" + studyResult + '\'' +
                ", graduateTime=" + graduateTime +
                ", college='" + college + '\'' +
                ", degree='" + degree + '\'' +
                ", collegeType='" + collegeType + '\'' +
                '}';
    }

    public EducationVO(EducationPO educationPO) {
        if (educationPO != null) {
            this.color = educationPO.getColor();
            this.name = educationPO.getName();
            this.studyResult = educationPO.getStudyResult();
            this.graduateTime = educationPO.getGraduateTime();
            this.college = educationPO.getCollege();
            this.degree = educationPO.getDegree();
            this.collegeType = educationPO.getCollegeType();
        }
    }

    public String getName() {
        return name;
    }

    public String getStudyResult() {
        return studyResult;
    }

    public String getGraduateTime() {
        return graduateTime;
    }

    public String getCollege() {
        return college;
    }

    public String getDegree() {
        return degree;
    }

    public String getCollegeType() {
        return collegeType;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
