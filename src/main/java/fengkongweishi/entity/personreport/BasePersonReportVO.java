package fengkongweishi.entity.personreport;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.enums.Level;
import fengkongweishi.enums.SystemEditionEnum;
import fengkongweishi.handle.DateTimeSerializer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * @author huanghengkun
 * @date 2018/01/24
 */
public abstract class BasePersonReportVO {

    private Date createAt;
    private Date finishAt;
    private String level;
    private String pdfUrl;
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    private SystemEditionEnum edition;
    private Integer customerId;

    public BasePersonReportVO(PersonReport report, Level level) {
        this.createAt = report.getCreateAt();
        this.finishAt = report.getFinishAt();
        this.imgUrl = report.getImgUrl();
        this.pdfUrl = report.getPdfUrl();
        this.level = level == null ? "" : level.getMessage();
        this.customerId = report.getCustomer().getId();
        this.edition = report.getEdition();
    }

    public static BasePersonReportVO create(PersonReport report, Level level) {
        switch (report.getEdition()) {
            case PERSONJUNIOR:
                return new PersonReportJuniorVO(report, level);
            case PERSONMOBILE:
                return new PersonReportMobileVO(report, level);
            case PERSONECOMMERCE:
                return new PersonReportECommerceVO(report, level);
            case PERSONSENIOR:
                return new PersonReportSeniorVO(report, level);
            default:
                throw new IllegalArgumentException("版本号错误");
        }
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreateAt() {
        return createAt;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getFinishAt() {
        return finishAt;
    }

    public String getLevel() {
        return level;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public SystemEditionEnum getEdition() {
        return edition;
    }

    public Integer getCustomerId() {
        return customerId;
    }

}
