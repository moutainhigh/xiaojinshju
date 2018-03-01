package fengkongweishi.entity.personreport.bo;

import java.util.Objects;

/**
 * 刑事犯罪
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class Criminal {
    /**
     * 涉案类型（比中在逃、比中前科、比中涉毒、比中吸毒）
     */
    private String crimeType;
    /**
     * 案件数量
     */
    private Integer count;
    /**
     * 犯案类型
     */
    private String caseType;
    /**
     * 案件来源
     */
    private String caseSource;
    /**
     * 刑法时长
     */
    private String casePeriod;
    /**
     * 案件等级
     */
    private String caseLevel;


    public Criminal(String crimeType, Integer count, String caseType, String caseSource, String casePeriod, String caseLevel) {
        this.crimeType = crimeType;
        this.count = count;
        this.caseType = caseType;
        this.caseSource = caseSource;
        this.casePeriod = casePeriod;
        this.caseLevel = caseLevel;
    }

    public Criminal() {
    }

    @Override
    public String toString() {
        return "Criminal{" +
                "crimeType='" + crimeType + '\'' +
                ", count=" + count +
                ", caseType='" + caseType + '\'' +
                ", caseSource='" + caseSource + '\'' +
                ", casePeriod='" + casePeriod + '\'' +
                ", caseLevel='" + caseLevel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Criminal criminal = (Criminal) o;
        return Objects.equals(crimeType, criminal.crimeType) &&
                Objects.equals(count, criminal.count) &&
                Objects.equals(caseType, criminal.caseType) &&
                Objects.equals(caseSource, criminal.caseSource) &&
                Objects.equals(casePeriod, criminal.casePeriod) &&
                Objects.equals(caseLevel, criminal.caseLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crimeType, count, caseType, caseSource, casePeriod, caseLevel);
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource;
    }

    public String getCasePeriod() {
        return casePeriod;
    }

    public void setCasePeriod(String casePeriod) {
        this.casePeriod = casePeriod;
    }

    public String getCaseLevel() {
        return caseLevel;
    }

    public void setCaseLevel(String caseLevel) {
        this.caseLevel = caseLevel;
    }
}
