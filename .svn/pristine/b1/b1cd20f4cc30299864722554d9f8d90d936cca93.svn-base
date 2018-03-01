package fengkongweishi.entity.personreport.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.handle.DateSerializer;

import java.util.Date;
import java.util.Objects;

/**
 * 法院裁决
 *
 * @author huanghengkun
 * @date 2018/01/09
 */
public class CourtJudgment {
    /**
     * 文书编号
     */
    private String docId;
    /**
     * 案件标题
     */
    private String title;
    /**
     * 法院名称
     */
    private String court;
    /**
     * 案件类别
     */
    private String caseType;
    /**
     * 案号
     */
    private String caseNum;
    /**
     * 案由
     */
    private String caseCause;
    /**
     * 案由编码类型
     */
    private String caseCauseCode;
    /**
     * 上述人
     */
    private String appellant;
    /**
     * 被上述人
     */
    private String appellee;
    /**
     * 审结时间
     */
    private Date concludeTime;
    /**
     * 判决结果
     */
    private String judgeResult;
    /**
     * 案例摘要
     */
    private String content;
    /**
     * 审理程序
     */
    private String trialProcedure;

    private boolean queried = false;

    @Override
    public String toString() {
        return "CourtJudgment{" +
                "docId='" + docId + '\'' +
                ",title='" + title + '\'' +
                ", court='" + court + '\'' +
                ", caseType='" + caseType + '\'' +
                ", caseNum='" + caseNum + '\'' +
                ", caseCause='" + caseCause + '\'' +
                ", caseCauseCode='" + caseCauseCode + '\'' +
                ", appellant='" + appellant + '\'' +
                ", appellee='" + appellee + '\'' +
                ", concludeTime=" + concludeTime +
                ", judgeResult='" + judgeResult + '\'' +
                ", content='" + content + '\'' +
                ", trialProcedure='" + trialProcedure + '\'' +
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
        CourtJudgment that = (CourtJudgment) o;
        return Objects.equals(docId, that.docId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docId);
    }

    public CourtJudgment(String docId, String title, String caseType, Date concludeTime, String content) {
        this.docId = docId;
        this.title = title;
        this.caseType = caseType;
        this.concludeTime = concludeTime;
        this.content = content;
    }

    public CourtJudgment() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public String getCaseCause() {
        return caseCause;
    }

    public void setCaseCause(String caseCause) {
        this.caseCause = caseCause;
    }

    public String getCaseCauseCode() {
        return caseCauseCode;
    }

    public void setCaseCauseCode(String caseCauseCode) {
        this.caseCauseCode = caseCauseCode;
    }

    public String getAppellant() {
        return appellant;
    }

    public void setAppellant(String appellant) {
        this.appellant = appellant;
    }

    public String getAppellee() {
        return appellee;
    }

    public void setAppellee(String appellee) {
        this.appellee = appellee;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getConcludeTime() {
        return concludeTime;
    }

    public void setConcludeTime(Date concludeTime) {
        this.concludeTime = concludeTime;
    }

    public String getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(String judgeResult) {
        this.judgeResult = judgeResult;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTrialProcedure() {
        return trialProcedure;
    }

    public void setTrialProcedure(String trialProcedure) {
        this.trialProcedure = trialProcedure;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public boolean isQueried() {
        return queried;
    }

    public void setQueried(boolean queried) {
        this.queried = queried;
    }
}
