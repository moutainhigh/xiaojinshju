package fengkongweishi.entity.personreport;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fengkongweishi.entity.personreport.vo.*;
import fengkongweishi.enums.Level;
import fengkongweishi.enums.SystemEditionEnum;
import fengkongweishi.handle.DateTimeSerializer;

import java.util.Date;

/**
 * 全面版视图
 *
 * @author huanghengkun
 * @date 2018/02/28
 */
public class PersonReportSeniorVO extends BasePersonReportVO {

    private String name;
    private String idcard;
    private String mobile;
    private String bankcard;
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
    private OverdueCreditVO overdueCreditVO;
    private DishonestBlackVO dishonestBlackVO;
    private CriminalVO criminalVO;
    private CourtJudgmentVO courtJudgmentVO;
    private ViolationVO violationVO;

    private EducationVO educationVO;

    private CarrierVO carrierVO;
    private CallRiskVO callRiskVO;
    private SocialContactVO socialContactVO;
    private CallListVO callListVO;

    private WealthVO wealthVO;
    private ConsumeVO consumeVO;
    private DeliverAddressVO deliverAddressVO;

    public PersonReportSeniorVO(PersonReport report, Level level) {
        super(report, level);
        this.name = report.getName();
        this.idcard = report.getIdCard();
        this.mobile = report.getMobile();
        this.bankcard = report.getBankCard();

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

        this.idCardVO = new IdCardVO(report.getIdCardPO(), report.getBankPO(), report.getPhonePO());
        this.idCheckVO = new IdCheckVO(report.getIdCheckPO());
        this.zhimaScoreVO = new ZhimaScoreVO(report.getZhimaScorePO());
        this.fraudVO = new FraudVO(report.getBlackRiskPO());
        this.multipleHeadLendVO = new MultipleHeadLendVO(report.getBlackRiskPO());
        this.overdueCreditVO = new OverdueCreditVO(report.getBlackRiskPO());
        this.dishonestBlackVO = new DishonestBlackVO(report.getDishonestBlackPO());
        this.criminalVO = new CriminalVO(report.getCriminalPO());
        this.courtJudgmentVO = new CourtJudgmentVO(report.getCourtJudgmentPO());
        this.educationVO = new EducationVO(report.getEducationPO());

        this.violationVO = report.getViolationPO() != null ? new ViolationVO(report.getViolationPO()) : null;

        this.carrierVO = report.getCarrierPO() != null ? new CarrierVO(report.getCarrierPO()) : null;
        this.callRiskVO = report.getCarrierPO() != null ? new CallRiskVO(report.getCarrierPO()) : null;
        this.socialContactVO = report.getCarrierPO() != null ? new SocialContactVO(report.getCarrierPO()) : null;
        this.callListVO = report.getCarrierPO() != null ? new CallListVO(report.getCarrierPO()) : null;

        this.consumeVO = report.getTaoBaoPO() != null ? new ConsumeVO(report.getTaoBaoPO()) : null;
        this.deliverAddressVO = report.getTaoBaoPO() != null ? new DeliverAddressVO(report.getTaoBaoPO()) : null;
        this.overdueCreditVO = report.getTaoBaoPO() != null ? new OverdueCreditVO(report.getBlackRiskPO()) : null;
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

    public OverdueCreditVO getOverdueCreditVO() {
        return overdueCreditVO;
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

    public ViolationVO getViolationVO() {
        return violationVO;
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

    public DeliverAddressVO getDeliverAddressVO() {
        return deliverAddressVO;
    }

    @Override
    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreateAt() {
        return super.getCreateAt();
    }

    @Override
    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getFinishAt() {
        return super.getFinishAt();
    }

    @Override
    public String getLevel() {
        return super.getLevel();
    }

    @Override
    public String getPdfUrl() {
        return super.getPdfUrl();
    }

    @Override
    public String getImgUrl() {
        return super.getImgUrl();
    }

    @Override
    public SystemEditionEnum getEdition() {
        return super.getEdition();
    }

    @Override
    public Integer getCustomerId() {
        return super.getCustomerId();
    }
}
