package fengkongweishi.entity.personreport;

import fengkongweishi.annotation.Info;
import fengkongweishi.entity.base.BaseEntity;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.customer.Customer;
import fengkongweishi.entity.customersearchlog.CustomerSearchLog;
import fengkongweishi.entity.personreport.po.*;
import fengkongweishi.entity.user.User;
import fengkongweishi.enums.MoxieTaskStatusEnum;
import fengkongweishi.enums.SearchStatusEnum;
import fengkongweishi.enums.SystemEditionEnum;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * 个信查询报告
 *
 * @author huanghengkun
 * @date 2018/01/15
 */
@Entity
@Table(indexes = {@Index(name = "idx_person_report_mobile", columnList = "mobile"),
        @Index(name = "idx_person_report_id_card", columnList = "idCard")})
public class PersonReport extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCardId")
    private IdCardPO idCardPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "phoneId")
    private PhonePO phonePO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bankId")
    private BankPO bankPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCheckId")
    private IdCheckPO idCheckPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "zhimaScoreId")
    private ZhimaScorePO zhimaScorePO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "blackRiskId")
    private BlackRiskPO blackRiskPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dishonestBlackId")
    private DishonestBlackPO dishonestBlackPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "criminalId")
    private CriminalPO criminalPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "courtJudgmentId")
    private CourtJudgmentPO courtJudgmentPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "educationId")
    private EducationPO educationPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "carrierId")
    private CarrierPO carrierPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "violationId")
    private ViolationPO violationPO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "taoBaoId")
    private TaoBaoPO taoBaoPO;

    private Date createAt;
    @Enumerated(EnumType.STRING)
    private SearchStatusEnum status;
    @Enumerated(EnumType.STRING)
    private SystemEditionEnum edition;
    private String name;
    private String mobile;
    private String idCard;
    private String bankCard;
    private String commonAddress;
    private String servicePassword;
    private String linkman1Name;
    private String linkman1Relationship;
    private String linkman1Mobile;
    private String linkman2Name;
    private String linkman2Relationship;
    private String linkman2Mobile;
    @Column(unique = true)
    private String moxieTaskTaobao;
    @Enumerated(EnumType.STRING)
    private MoxieTaskStatusEnum moxieTaskTaobaoStatus;
    @Column(unique = true)
    private String moxieTaskCarrier;
    @Enumerated(EnumType.STRING)
    private MoxieTaskStatusEnum moxieTaskCarrierStatus;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "createByCompany")
    private Company createByCompany;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "createBy")
    private User createBy;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @OneToOne(mappedBy = "report")
    private CustomerSearchLog customerSearchLog;

    private Date finishAt;

    @Info(label = "车牌号码", tip = "", placeholder = "", help = "", secret = "")
    private String plateNumber;

    @Info(label = "车架号 根据管局需要输入", tip = "", placeholder = "", help = "", secret = "")
    private String vin;

    @Info(label = "发动机号 根据管局需要输入", tip = "", placeholder = "", help = "", secret = "")
    private String engineNo;

    @Info(label = "车牌类型", tip = "", placeholder = "", help = "", secret = "")
    private String carType;

    @Info(label = "pdfUrl", tip = "", placeholder = "", help = "", secret = "")
    private String pdfUrl;

    @Info(label = "图片URL", tip = "", placeholder = "", help = "", secret = "")
    private String imgUrl;

    public PersonReport() {
    }

    /**
     * 基础构造器,除了传入参数,还初始化createAt,status
     *
     * @see BaseParameterDTO
     */
    public PersonReport(String name, String mobile, String idCard, String bankCard, String commonAddress, SystemEditionEnum edition) {
        this.name = name;
        this.mobile = mobile;
        this.idCard = idCard;
        this.bankCard = bankCard;
        this.commonAddress = commonAddress;
        this.createAt = new Date();
        this.status = SearchStatusEnum.PROCESSING;
        this.edition = edition;
    }

    public String joinCarrierContact() {
        String linkman1 = "";
        String linkman2 = "";
        if (!StringUtils.isEmpty(this.linkman1Mobile) && !StringUtils.isEmpty(this.linkman1Name)
                && !StringUtils.isEmpty(this.linkman1Relationship)) {
            linkman1 = this.linkman1Mobile + ":" + this.linkman1Name + ":" + this.linkman1Relationship;
        }
        if (!StringUtils.isEmpty(this.linkman2Mobile) && !StringUtils.isEmpty(this.linkman2Name)
                && !StringUtils.isEmpty(this.linkman2Relationship)) {
            linkman2 = this.linkman2Mobile + ":" + this.linkman2Name + ":" + this.linkman2Relationship;
        }
        if ("".equals(linkman1) && "".equals(linkman2)) {
            return "";
        }
        if ("".equals(linkman1) || "".equals(linkman2)) {
            return "".equals(linkman1) ? linkman2 : linkman1;
        }
        return linkman1 + "," + linkman2;
    }

    public void pushMoxieTaskStatus(MoxieTaskStatusEnum newMmoxieTaskCarrierStatus) {
        if (isStatusLater(this.moxieTaskCarrierStatus, newMmoxieTaskCarrierStatus)) {
            this.moxieTaskCarrierStatus = newMmoxieTaskCarrierStatus;
        }
    }

    private boolean isStatusLater(MoxieTaskStatusEnum oldStatus, MoxieTaskStatusEnum newStatus) {
        return oldStatus == null || oldStatus.compareTo(newStatus) < 0;
    }

    public int reportPrice() {
        return this.getEdition().getPrice();
    }


    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMoxieTaskTaobao() {
        return moxieTaskTaobao;
    }

    public void setMoxieTaskTaobao(String moxieTaskTaobao) {
        this.moxieTaskTaobao = moxieTaskTaobao;
    }

    public MoxieTaskStatusEnum getMoxieTaskTaobaoStatus() {
        return moxieTaskTaobaoStatus;
    }

    public void setMoxieTaskTaobaoStatus(MoxieTaskStatusEnum moxieTaskTaobaoStatus) {
        this.moxieTaskTaobaoStatus = moxieTaskTaobaoStatus;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public SearchStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SearchStatusEnum status) {
        this.status = status;
    }

    public SystemEditionEnum getEdition() {
        return edition;
    }

    public void setEdition(SystemEditionEnum edition) {
        this.edition = edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(String commonAddress) {
        this.commonAddress = commonAddress;
    }

    public String getServicePassword() {
        return servicePassword;
    }

    public void setServicePassword(String servicePassword) {
        this.servicePassword = servicePassword;
    }

    public String getLinkman1Name() {
        return linkman1Name;
    }

    public void setLinkman1Name(String linkman1Name) {
        this.linkman1Name = linkman1Name;
    }

    public String getLinkman1Relationship() {
        return linkman1Relationship;
    }

    public void setLinkman1Relationship(String linkman1Relationship) {
        this.linkman1Relationship = linkman1Relationship;
    }

    public String getLinkman1Mobile() {
        return linkman1Mobile;
    }

    public void setLinkman1Mobile(String linkman1Mobile) {
        this.linkman1Mobile = linkman1Mobile;
    }

    public String getLinkman2Name() {
        return linkman2Name;
    }

    public void setLinkman2Name(String linkman2Name) {
        this.linkman2Name = linkman2Name;
    }

    public String getLinkman2Relationship() {
        return linkman2Relationship;
    }

    public void setLinkman2Relationship(String linkman2Relationship) {
        this.linkman2Relationship = linkman2Relationship;
    }

    public String getLinkman2Mobile() {
        return linkman2Mobile;
    }

    public void setLinkman2Mobile(String linkman2Mobile) {
        this.linkman2Mobile = linkman2Mobile;
    }

    public String getMoxieTaskCarrier() {
        return moxieTaskCarrier;
    }

    public void setMoxieTaskCarrier(String moxieTaskCarrier) {
        this.moxieTaskCarrier = moxieTaskCarrier;
    }

    public MoxieTaskStatusEnum getMoxieTaskCarrierStatus() {
        return moxieTaskCarrierStatus;
    }

    public void setMoxieTaskCarrierStatus(MoxieTaskStatusEnum moxieTaskCarrierStatus) {
        this.moxieTaskCarrierStatus = moxieTaskCarrierStatus;
    }

    public CustomerSearchLog getCustomerSearchLog() {
        return customerSearchLog;
    }

    public void setCustomerSearchLog(CustomerSearchLog customerSearchLog) {
        this.customerSearchLog = customerSearchLog;
    }

    public Company getCreateByCompany() {
        return createByCompany;
    }

    public void setCreateByCompany(Company createByCompany) {
        this.createByCompany = createByCompany;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(Date finishAt) {
        this.finishAt = finishAt;
    }

    public IdCardPO getIdCardPO() {
        return idCardPO;
    }

    public void setIdCardPO(IdCardPO idCardPO) {
        this.idCardPO = idCardPO;
    }

    public PhonePO getPhonePO() {
        return phonePO;
    }

    public void setPhonePO(PhonePO phonePO) {
        this.phonePO = phonePO;
    }

    public BankPO getBankPO() {
        return bankPO;
    }

    public void setBankPO(BankPO bankPO) {
        this.bankPO = bankPO;
    }

    public IdCheckPO getIdCheckPO() {
        return idCheckPO;
    }

    public void setIdCheckPO(IdCheckPO idCheckPO) {
        this.idCheckPO = idCheckPO;
    }

    public ZhimaScorePO getZhimaScorePO() {
        return zhimaScorePO;
    }

    public void setZhimaScorePO(ZhimaScorePO zhimaScorePO) {
        this.zhimaScorePO = zhimaScorePO;
    }

    public BlackRiskPO getBlackRiskPO() {
        return blackRiskPO;
    }

    public void setBlackRiskPO(BlackRiskPO blackRiskPO) {
        this.blackRiskPO = blackRiskPO;
    }

    public DishonestBlackPO getDishonestBlackPO() {
        return dishonestBlackPO;
    }

    public void setDishonestBlackPO(DishonestBlackPO dishonestBlackPO) {
        this.dishonestBlackPO = dishonestBlackPO;
    }

    public CriminalPO getCriminalPO() {
        return criminalPO;
    }

    public void setCriminalPO(CriminalPO criminalPO) {
        this.criminalPO = criminalPO;
    }

    public CourtJudgmentPO getCourtJudgmentPO() {
        return courtJudgmentPO;
    }

    public void setCourtJudgmentPO(CourtJudgmentPO courtJudgmentPO) {
        this.courtJudgmentPO = courtJudgmentPO;
    }

    public EducationPO getEducationPO() {
        return educationPO;
    }

    public void setEducationPO(EducationPO educationPO) {
        this.educationPO = educationPO;
    }

    public CarrierPO getCarrierPO() {
        return carrierPO;
    }

    public void setCarrierPO(CarrierPO carrierPO) {
        this.carrierPO = carrierPO;
    }

    public ViolationPO getViolationPO() {
        return violationPO;
    }

    public void setViolationPO(ViolationPO violationPO) {
        this.violationPO = violationPO;
    }

    public TaoBaoPO getTaoBaoPO() {
        return taoBaoPO;
    }

    public void setTaoBaoPO(TaoBaoPO taoBaoPO) {
        this.taoBaoPO = taoBaoPO;
    }
}
