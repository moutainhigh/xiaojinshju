package fengkongweishi.entity.personreport;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.entity.personreport.vo.*;
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
public class PersonReportVO {
    private String name;
    private String idcard;
    private String mobile;
    private String bankcard;
    private Date createAt;
    private Date finishAt;
    private String level;
    private String pdfUrl;
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    private SystemEditionEnum edition;
    private Integer customerId;
    private String commonAddress;
    private String servicePassword;
    private String linkman1Name;
    private String linkman1Relationship;
    private String linkman1Mobile;
    private String linkman2Name;
    private String linkman2Relationship;
    private String linkman2Mobile;
    private String plateNumber;
    private String vin;
    private String engineNo;
    private String carType;

    private IdCardVO idCardVO;
    private IdCheckVO idCheckVO;
    private ZhimaScoreVO zhimaScoreVO;
    private FraudVO fraudVO;
    private MultipleHeadLendVO multipleHeadLendVO;
    private DishonestBlackVO dishonestBlackVO;
    private CriminalVO criminalVO;
    private CourtJudgmentVO courtJudgmentVO;
    private EducationVO educationVO;
    private CarrierVO carrierVO;
    private CallRiskVO callRiskVO;
    private SocialContactVO socialContactVO;
    private CallListVO callListVO;
    private WealthVO wealthVO;
    private ConsumeVO consumeVO;
    private ViolationVO violationVO;
    private DeliverAddressVO deliverAddressVO;
    private OverdueCreditVO overdueCreditVO;

    @Override
    public String toString() {
        return "PersonReportVO{" +
                "name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bankcard='" + bankcard + '\'' +
                ", createAt=" + createAt +
                ", finishAt=" + finishAt +
                ", level='" + level + '\'' +
                ", pdfUrl='" + pdfUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", edition=" + edition +
                ", customerId=" + customerId +
                ", commonAddress='" + commonAddress + '\'' +
                ", servicePassword='" + servicePassword + '\'' +
                ", linkman1Name='" + linkman1Name + '\'' +
                ", linkman1Relationship='" + linkman1Relationship + '\'' +
                ", linkman1Mobile='" + linkman1Mobile + '\'' +
                ", linkman2Name='" + linkman2Name + '\'' +
                ", linkman2Relationship='" + linkman2Relationship + '\'' +
                ", linkman2Mobile='" + linkman2Mobile + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", vin='" + vin + '\'' +
                ", engineNo='" + engineNo + '\'' +
                ", carType='" + carType + '\'' +
                ", idCardVO=" + idCardVO +
                ", idCheckVO=" + idCheckVO +
                ", zhimaScoreVO=" + zhimaScoreVO +
                ", fraudVO=" + fraudVO +
                ", multipleHeadLendVO=" + multipleHeadLendVO +
                ", dishonestBlackVO=" + dishonestBlackVO +
                ", criminalVO=" + criminalVO +
                ", courtJudgmentVO=" + courtJudgmentVO +
                ", educationVO=" + educationVO +
                ", carrierVO=" + carrierVO +
                ", callRiskVO=" + callRiskVO +
                ", socialContactVO=" + socialContactVO +
                ", callListVO=" + callListVO +
                ", wealthVO=" + wealthVO +
                ", consumeVO=" + consumeVO +
                ", violationVO=" + violationVO +
                ", deliverAddressVO=" + deliverAddressVO +
                ", overdueCreditVO=" + overdueCreditVO +
                '}';
    }

    public PersonReportVO(PersonReport report, Level level) {
        this.name = report.getName();
        this.idcard = report.getIdCard();
        this.mobile = report.getMobile();
        this.bankcard = report.getBankCard();
        this.createAt = report.getCreateAt();
        this.finishAt = report.getFinishAt();
        this.imgUrl = report.getImgUrl();
        this.pdfUrl = report.getPdfUrl();
        this.level = level == null ? "" : level.getMessage();
        this.customerId = report.getCustomer().getId();
        this.edition = report.getEdition();
        this.commonAddress = report.getCommonAddress();
        this.servicePassword = report.getServicePassword();
        this.linkman1Mobile = report.getLinkman1Mobile();
        this.linkman1Relationship = report.getLinkman1Relationship();
        this.linkman1Name = report.getLinkman1Name();
        this.linkman2Mobile = report.getLinkman2Mobile();
        this.linkman2Relationship = report.getLinkman2Relationship();
        this.linkman2Name = report.getLinkman2Name();
        this.plateNumber = report.getPlateNumber();
        this.vin = report.getVin();
        this.engineNo = report.getEngineNo();
        this.carType = report.getCarType();

        this.idCardVO = new IdCardVO(report.getIdCardPO());
        this.idCheckVO = new IdCheckVO(report.getIdCheckPO());
        this.zhimaScoreVO = new ZhimaScoreVO(report.getZhimaScorePO());
        this.fraudVO = new FraudVO(report.getBlackRiskPO());
        this.multipleHeadLendVO = new MultipleHeadLendVO(report.getBlackRiskPO());
        this.dishonestBlackVO = new DishonestBlackVO(report.getDishonestBlackPO());
        this.criminalVO = new CriminalVO(report.getCriminalPO());
        this.courtJudgmentVO = new CourtJudgmentVO(report.getCourtJudgmentPO());
        this.educationVO = new EducationVO(report.getEducationPO());
        this.carrierVO = new CarrierVO(report.getCarrierPO());
        this.callRiskVO = new CallRiskVO(report.getCarrierPO());
        this.socialContactVO = new SocialContactVO(report.getCarrierPO());
        this.callListVO = new CallListVO(report.getCarrierPO());
        this.wealthVO = new WealthVO(report.getTaoBaoPO());
        this.violationVO = new ViolationVO(report.getViolationPO());
        this.consumeVO = new ConsumeVO(report.getTaoBaoPO());
        this.deliverAddressVO = new DeliverAddressVO(report.getTaoBaoPO());
        this.overdueCreditVO = new OverdueCreditVO(report.getBlackRiskPO());
    }


    public String getName() {
        return name;
    }

    public String getIdcard() {
        return idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public String getBankcard() {
        return bankcard;
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

    public String getCommonAddress() {
        return commonAddress;
    }

    public String getServicePassword() {
        return servicePassword;
    }

    public String getLinkman1Name() {
        return linkman1Name;
    }

    public String getLinkman1Relationship() {
        return linkman1Relationship;
    }

    public String getLinkman1Mobile() {
        return linkman1Mobile;
    }

    public String getLinkman2Name() {
        return linkman2Name;
    }

    public String getLinkman2Relationship() {
        return linkman2Relationship;
    }

    public String getLinkman2Mobile() {
        return linkman2Mobile;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getVin() {
        return vin;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public String getCarType() {
        return carType;
    }

    public IdCardVO getIdCardVO() {
        return idCardVO;
    }

    public IdCheckVO getIdCheckVO() {
        return idCheckVO;
    }

    public ZhimaScoreVO getZhimaScoreVO() {
        return zhimaScoreVO;
    }

    public FraudVO getFraudVO() {
        return fraudVO;
    }

    public MultipleHeadLendVO getMultipleHeadLendVO() {
        return multipleHeadLendVO;
    }

    public DishonestBlackVO getDishonestBlackVO() {
        return dishonestBlackVO;
    }

    public CriminalVO getCriminalVO() {
        return criminalVO;
    }

    public CourtJudgmentVO getCourtJudgmentVO() {
        return courtJudgmentVO;
    }

    public EducationVO getEducationVO() {
        return educationVO;
    }

    public CarrierVO getCarrierVO() {
        return carrierVO;
    }

    public CallRiskVO getCallRiskVO() {
        return callRiskVO;
    }

    public SocialContactVO getSocialContactVO() {
        return socialContactVO;
    }

    public CallListVO getCallListVO() {
        return callListVO;
    }

    public WealthVO getWealthVO() {
        return wealthVO;
    }

    public ConsumeVO getConsumeVO() {
        return consumeVO;
    }

    public ViolationVO getViolationVO() {
        return violationVO;
    }

    public DeliverAddressVO getDeliverAddressVO() {
        return deliverAddressVO;
    }

    public OverdueCreditVO getOverdueCreditVO() {
        return overdueCreditVO;
    }
}
