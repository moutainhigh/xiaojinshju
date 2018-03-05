package fengkongweishi.service.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.bo.*;
import fengkongweishi.entity.personreport.po.*;
import fengkongweishi.entity.personreport.vo.RiskItem;
import fengkongweishi.enums.Color;
import fengkongweishi.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;

/**
 * 风险评估服务
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Service
public class AnalyseService {

    private Logger logger = LoggerFactory.getLogger(AnalyseService.class);

    @Autowired
    FetchService fetchService;
    @Autowired
    MoxieService moxieService;

    @Async
    public Future<Map> asyncHandleIdCard(PersonReport report) {
        String name = report.getName();
        String idCard = report.getIdCard();
        String mobile = report.getMobile();
        String bankCard = report.getBankCard();
        String address = report.getCommonAddress();

        Map<String, IAnalyseItem> resultMap = new HashMap<>();
        IdCardPO idCardPO = new IdCardPO(Color.SUCCESS);
        PhonePO phonePO = new PhonePO(Color.SUCCESS);
        BankPO bankPO = new BankPO(Color.SUCCESS);

        Map idCardResult = fetchService.fetchIdCard(idCard);
        Map mobileResult = fetchService.fetchMobile(mobile);
        Map bankCardResult = fetchService.fetchBankCard(bankCard);
        idCardPO.setName(name);
        idCardPO.setIdCard(idCard);
        idCardPO.setAddress(address);
        phonePO.setMobile(mobile);
        bankPO.setBankCard(bankCard);
        if (idCardResult != null) {
            if ("0".equals(idCardResult.get("status").toString())) {
                HashMap result = (HashMap) idCardResult.get("result");
                idCardPO.setSex(result.get("sex").toString());
                idCardPO.setProvince(result.get("province").toString());
                idCardPO.setCity(result.get("city").toString());
                idCardPO.setTown(result.get("town").toString());
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                    Date birth = sdf.parse(result.get("birth").toString());
                    int year1 = Calendar.getInstance().get(Calendar.YEAR);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(birth);
                    int year2 = calendar.get(Calendar.YEAR);
                    idCardPO.setAge(year1 - year2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                idCardPO.setColor(Color.ERROR);
            }
        } else {
            idCardPO.setColor(Color.TIMEOUT);
        }
        if (mobileResult != null) {
            if ("0".equals(mobileResult.get("error_code").toString())) {
                HashMap result = (HashMap) mobileResult.get("result");
                String mobileCompany = result.get("city").toString() + result.get("company").toString().substring(2);
                phonePO.setMobileCompany(mobileCompany);
            } else {
                phonePO.setColor(Color.ERROR);
            }
        } else {
            phonePO.setColor(Color.TIMEOUT);
        }
        if (bankCardResult != null) {
            if ("0".equals(bankCardResult.get("error_code").toString())) {
                HashMap result = (HashMap) bankCardResult.get("result");
                String bankName = result.get("bankname").toString();
                bankPO.setBankName(bankName);
            } else {
                bankPO.setColor(Color.ERROR);
            }
        } else {
            bankPO.setColor(Color.TIMEOUT);
        }
        resultMap.put("idCardPO", idCardPO);
        resultMap.put("phonePO", phonePO);
        resultMap.put("bankPO", bankPO);
        return new AsyncResult<>(resultMap);
    }

    @Async
    public Future<IdCheckPO> asyncHandleIdCheck(PersonReport report) {
        String name = report.getName();
        String idCard = report.getIdCard();
        String mobile = report.getMobile();
        String bankCard = report.getBankCard();
        String address = report.getCommonAddress();
        Map antiFraudResult = fetchService.fetchAlipayAntiFraud(name, idCard, mobile, bankCard, address);
        IdCheckPO idCheckPO = new IdCheckPO();
        if (antiFraudResult != null) {
            List<String> verifyCode = (ArrayList<String>) (antiFraudResult.get("verifyCode"));
            Optional<String> cnStr = verifyCode.stream()
                    .filter(str -> verifyTable.containsKey(str) && str.startsWith("V_CN")).findFirst();
            Optional<String> phStr = verifyCode.stream()
                    .filter(str -> verifyTable.containsKey(str) && str.startsWith("V_PH")).findFirst();
            Optional<String> bcStr = verifyCode.stream()
                    .filter(str -> verifyTable.containsKey(str) && str.startsWith("V_BC")).findFirst();
            Optional<String> adStr = verifyCode.stream()
                    .filter(str -> verifyTable.containsKey(str) && str.startsWith("V_AD")).findFirst();
            if (cnStr.isPresent() && phStr.isPresent()) {
                if ("V_CN_NA".equals(cnStr.get()) || "V_CN_NM_UM".equals(cnStr.get()) || "V_PH_NA".equals(phStr.get())
                        || "V_PH_CN_UM".equals(phStr.get())) {
                    idCheckPO.setNmPhCn("姓名、手机号、身份证不一致");
                    idCheckPO.setColor(Color.DANGER);
                } else {
                    RiskItem phRisk = verifyTable.get(phStr.get());
                    String useTime = phRisk.getContent().substring(phRisk.getContent().indexOf(",") + 1);
                    idCheckPO.setNmPhCn("姓名、手机号、身份证一致," + useTime);
                    idCheckPO.setColor(phRisk.getColor());
                }
            }
            if (bcStr.isPresent()) {
                RiskItem bkRisk = verifyTable.get(bcStr.get());
                idCheckPO.setBcCn(bkRisk.getContent());
                idCheckPO.setColor(bkRisk.getColor());
            }
            if (adStr.isPresent()) {
                RiskItem adRisk = verifyTable.get(adStr.get());
                idCheckPO.setAdCn(adRisk.getContent());
                idCheckPO.setColor(adRisk.getColor());
            }
        } else {
            idCheckPO.setColor(Color.TIMEOUT);
        }
        return new AsyncResult<>(idCheckPO);
    }

    @Async
    public Future<EducationPO> asyncHandleEducation(PersonReport report) {
        String name = report.getName();
        String idCrad = report.getIdCard();
        EducationPO educationPO = new EducationPO();
        Map educationResult = fetchService.fetchEducation(name, idCrad);
        if (educationResult == null) {
            educationPO.setColor(Color.TIMEOUT);
        } else {
            if ("0000".equals(educationResult.get("resCode").toString())) {
                Map data = (HashMap) educationResult.get("data");
                if ("2012".equals(data.get("statusCode"))) {
                    educationPO.setColor(Color.SUCCESS);
                    Map result = (HashMap) data.get("result");
                    educationPO.setName(name);
                    educationPO.setStudyResult(result.get("studyResult").toString());
                    educationPO.setGraduateTime(result.get("graduateTime").toString());
                    educationPO.setCollege(result.get("college").toString());
                    educationPO.setDegree(result.get("degree").toString());
                    educationPO.setCollegeType(result.get("collegeCategory").toString());
                } else {
                    educationPO.setColor(Color.ERROR);
                }
            } else {
                educationPO.setColor(Color.ERROR);
            }
        }
        return new AsyncResult<>(educationPO);
    }

    @Async
    public Future<ZhimaScorePO> asyncHandleZhimaScore(PersonReport report) {
        String name = report.getName();
        String idCard = report.getIdCard();
        ZhimaScorePO zhimaScorePO = new ZhimaScorePO();
        Map zhimaScoreResult = fetchService.fetchZhimaScore(name, idCard);
        if (zhimaScoreResult == null) {
            zhimaScorePO.setColor(Color.TIMEOUT);
        } else {
            if (zhimaScoreResult.get("score") == null) {
                zhimaScorePO.setColor(Color.ERROR);
            } else {
                String score = zhimaScoreResult.get("score").toString();
                zhimaScorePO.setScore(score);
                if ("400+".equals(score)) {
                    zhimaScorePO.setColor(Color.DANGER);
                } else if ("500+".equals(score)) {
                    zhimaScorePO.setColor(Color.WARNING);
                } else if ("600+".equals(score)) {
                    zhimaScorePO.setColor(Color.ATTENTION);
                } else if ("700+".equals(score)) {
                    zhimaScorePO.setColor(Color.SUCCESS);
                } else {
                    zhimaScorePO.setColor(Color.ERROR);
                }
            }
        }
        return new AsyncResult<>(zhimaScorePO);
    }

    @Async
    public Future<ViolationPO> asyncHandleViolation(PersonReport report) {
        String plateNumber = report.getPlateNumber();
        String vin = report.getVin();
        String engineNo = report.getEngineNo();
        String carType = report.getCarType();
        String mobile = report.getMobile();
        ViolationPO violationPO = new ViolationPO(Color.SUCCESS);
        Map violationResult = fetchService.fetchViolationXK(plateNumber, vin, engineNo, carType);
        if (violationResult != null && (boolean) violationResult.get("success")) {
            Map data = (Map) violationResult.get("data");
            List violationList = (ArrayList) data.get("violations");
            if (violationList != null && violationList.size() > 0) {
                violationPO.setColor(Color.DANGER);
                violationPO.setCount(violationList.size());
                JSONArray violations = new JSONArray();
                violations.forEach(row -> violations
                        .add(new Violation(((HashMap) row).get("time").toString(),
                                ((HashMap) row).get("address").toString(), ((HashMap) row).get("reason").toString(),
                                ((HashMap) row).get("fine").toString(), ((HashMap) row).get("point").toString(),
                                plateNumber, engineNo)));
                violationPO.setViolations(violations);
            } else {
                violationPO.setColor(Color.SUCCESS);
                violationPO.setCount(0);
            }
        } else {
            violationResult = fetchService.fetchViolationWS(plateNumber, vin, engineNo, carType, mobile);
            if (violationResult == null) {
                violationPO.setColor(Color.TIMEOUT);
            } else {
                if ("0".equals(violationResult.get("status").toString())) {
                    Map result = (Map) violationResult.get("result");
                    List list = (ArrayList) result.get("list");
                    if (list != null && list.size() > 0) {
                        violationPO.setColor(Color.DANGER);
                        violationPO.setCount(list.size());
                        JSONArray violations = new JSONArray();
                        list.forEach(row -> violations.add(new Violation(
                                ((HashMap) row).get("time").toString(), ((HashMap) row).get("address").toString(),
                                ((HashMap) row).get("content").toString(), ((HashMap) row).get("price").toString(),
                                ((HashMap) row).get("score").toString(), plateNumber, engineNo)));
                        violationPO.setViolations(violations);
                    } else {
                        violationPO.setColor(Color.SUCCESS);
                        violationPO.setCount(0);
                    }
                } else {
                    violationPO.setColor(Color.ERROR);
                }
            }
        }
        return new AsyncResult<>(violationPO);
    }

    @Async
    public Future<BlackRiskPO> asyncHandleBlackRisk(PersonReport report) {
        String idCard = report.getIdCard();
        String mobile = report.getMobile();
        String name = report.getName();
        String bankCard = report.getBankCard();
        BlackRiskPO blackRiskPO = new BlackRiskPO(Color.SUCCESS);
        Map result = fetchService.fetchXinShuRiskB(name, idCard, mobile, bankCard);
        if (result != null) {
            if ("0000".equals(result.get("rc").toString())) {
                Map data = (HashMap) result.get("data");
                List list = (ArrayList) data.get("list");
                JSONArray blackRisks = new JSONArray();
                for (Object o : list) {
                    Map item = (HashMap) o;
                    blackRisks.add(new BlackRiskItem((String) item.get("blackRiskType"),
                            (String) item.get("blackFactsType"),
                            (String) item.get("blackFacts"),
                            (String) item.get("blackAmt"),
                            (String) item.get("blackHappenDate"),
                            (String) item.get("blackDurationTime"),
                            (String) item.get("blackPublishSource")));
                }
                blackRiskPO.setBlackRisks(blackRisks);
            } else if ("0001".equals(result.get("rc").toString())) {
                // 0001 查询成功,无数据
                blackRiskPO.setColor(Color.SUCCESS);
            } else {
                blackRiskPO.setColor(Color.ERROR);
            }
        } else {
            blackRiskPO.setColor(Color.TIMEOUT);
        }
        return new AsyncResult<>(blackRiskPO);
    }

    @Async
    public Future<CriminalPO> asyncHandleCriminal(PersonReport report) {
        String name = report.getName();
        String idCard = report.getIdCard();
        CriminalPO criminalPO = new CriminalPO(Color.SUCCESS);
        Map criminalResult = fetchService.fetchCriminal(name, idCard);
        if (criminalResult == null) {
            criminalPO.setColor(Color.TIMEOUT);
        } else {
            if ("0000".equals(criminalResult.get("resCode").toString())) {
                Map businessData = (HashMap) criminalResult.get("data");
                if ("2012".equals(businessData.get("statusCode").toString())) {
                    List result = (ArrayList) businessData.get("result");
                    if (!result.isEmpty()) {
                        criminalPO.setColor(Color.DANGER);
                        criminalPO.setCount(result.size());
                        JSONArray criminals = new JSONArray();
                        result.forEach(row -> criminals
                                .add(new Criminal(((HashMap) row).get("crimeType").toString(),
                                        Integer.valueOf(((HashMap) row).get("count").toString()),
                                        ((HashMap) row).get("caseType").toString(),
                                        ((HashMap) row).get("caseSource").toString(),
                                        ((HashMap) row).get("casePeriod").toString(),
                                        ((HashMap) row).get("caseLevel").toString())));
                        criminalPO.setCriminals(criminals);
                    } else {
                        criminalPO.setColor(Color.SUCCESS);
                        criminalPO.setCount(0);
                    }
                } else if ("2007".equals(businessData.get("statusCode").toString())) {
                    criminalPO.setColor(Color.SUCCESS);
                    criminalPO.setCount(0);
                } else {
                    criminalPO.setColor(Color.ERROR);
                }
            } else {
                criminalPO.setColor(Color.ERROR);
            }
        }
        return new AsyncResult<>(criminalPO);
    }

    @Async
    public Future<DishonestBlackPO> asyncHandleDishonestBlack(PersonReport report) {
        String name = report.getName();
        String idCard = report.getIdCard();
        DishonestBlackPO dishonestBlackPO = new DishonestBlackPO(Color.SUCCESS);
        Map dishonestBlacklistResult = fetchService.fetchDishonestBlacklist(name, idCard);
        if (dishonestBlacklistResult == null) {
            dishonestBlackPO.setColor(Color.TIMEOUT);
        } else {
            List data = (ArrayList) dishonestBlacklistResult.get("data");
            if (data.size() > 0) {
                dishonestBlackPO.setColor(Color.DANGER);
                dishonestBlackPO.setCount(data.size());
                JSONArray dishonestBlacks = new JSONArray();
                data.forEach(row -> dishonestBlacks
                        .add(new DishonestBlack(((HashMap) row).get("duty").toString(),
                                ((HashMap) row).get("disrupt_type").toString(), ((HashMap) row).get("code").toString(),
                                ((HashMap) row).get("pub_time").toString(), ((HashMap) row).get("court").toString(),
                                ((HashMap) row).get("area").toString(), ((HashMap) row).get("performance").toString())));
                dishonestBlackPO.setDishonestBlacks(dishonestBlacks);
            } else {
                dishonestBlackPO.setColor(Color.SUCCESS);
                dishonestBlackPO.setCount(0);
            }
        }
        return new AsyncResult<>(dishonestBlackPO);
    }

    @Async
    public Future<CourtJudgmentPO> asyncHandleCourtJudgment(PersonReport report) {
        String name = report.getName();
        String idCard = report.getIdCard();
        CourtJudgmentPO courtJudgmentPO = new CourtJudgmentPO(Color.SUCCESS);
        Map courtJudgmentResult = fetchService.fetchCourtJudgment(name, idCard);
        if (courtJudgmentResult == null) {
            courtJudgmentPO.setColor(Color.TIMEOUT);
        } else {
            if ("0000".equals(courtJudgmentResult.get("resCode").toString())) {
                Map businessData = (HashMap) courtJudgmentResult.get("data");
                if ("2012".equals(businessData.get("statusCode").toString())) {
                    Map result = (HashMap) businessData.get("result");
                    Integer count = Integer.valueOf(result.get("totalCount").toString());
                    courtJudgmentPO.setCount(count);
                    if (count == 0) {
                        courtJudgmentPO.setColor(Color.SUCCESS);
                    } else {
                        courtJudgmentPO.setCourtJudgments(new HashSet<>());
                        ((ArrayList) result.get("docList")).stream().sorted(AnalyseService::compareConcludeTime)
                                .forEachOrdered(row -> {
                                    CourtJudgmentPOItem courtJudgmentPOItem = new CourtJudgmentPOItem(
                                            ((HashMap) row).get("docId").toString(),
                                            ((HashMap) row).get("title").toString(),
                                            ((HashMap) row).get("dataType").toString(),
                                            new Date(Long.valueOf(((HashMap) row).get("concludeTime").toString())),
                                            ((HashMap) row).get("content").toString(), courtJudgmentPO);
                                    courtJudgmentPO.getCourtJudgments().add(courtJudgmentPOItem);
                                });
                        courtJudgmentPO.setColor(Color.WARNING);
                        if (count > 1) {
                            courtJudgmentPO.setColor(Color.DANGER);
                        }
                    }
                } else if ("2007".equals(businessData.get("statusCode").toString())) {
                    courtJudgmentPO.setColor(Color.SUCCESS);
                    courtJudgmentPO.setCount(0);
                } else {
                    courtJudgmentPO.setColor(Color.ERROR);
                }
            } else {
                courtJudgmentPO.setColor(Color.ERROR);
            }
        }
        return new AsyncResult<>(courtJudgmentPO);
    }

    public void handleCourtJudgmentDetail(CourtJudgmentPOItem courtJudgmentPOItem) {
        Map detailResult = fetchService.fetchCourtJudgmentDetail(courtJudgmentPOItem.getDocId());
        if (!"0000".equals(detailResult.get("resCode"))) {
            return;
        }
        Map detailResultData = (HashMap) detailResult.get("data");
        if (!"2012".equals(detailResultData.get("statusCode"))) {
            return;
        }
        Map detailResultResult = (HashMap) detailResultData.get("result");
        // 解析原告和被告, 存在多个原告和被告的可能性
        List partyInfo = (ArrayList) detailResultResult.get("partyInfo");
        List<String> appellantList = new ArrayList<>();
        List<String> appelleeList = new ArrayList<>();
        for (Object itemObject : partyInfo) {
            Map item = (HashMap) itemObject;
            String callName = (String) item.get("callName");
            if (callName.contains("原告")) {
                appellantList.add((String) item.get("designation"));
            } else if (callName.contains("被告")) {
                appelleeList.add((String) item.get("designation"));
            }
        }
        String appellant = String.join(",", appellantList);
        String appellee = String.join(",", appelleeList);

        courtJudgmentPOItem.setAppellant(appellant);
        courtJudgmentPOItem.setAppellee(appellee);
        courtJudgmentPOItem.setCaseCause((String) detailResultResult.get("caseNum"));
        courtJudgmentPOItem.setCaseCauseCode((String) detailResultResult.get("caseCauseCode"));
        courtJudgmentPOItem.setCaseNum((String) detailResultResult.get("caseNum"));
        courtJudgmentPOItem.setCourt((String) detailResultResult.get("court"));
        courtJudgmentPOItem.setJudgeResult((String) detailResultResult.get("judgeResult"));
        courtJudgmentPOItem.setTrialProcedure((String) detailResultResult.get("trialProcedure"));
        courtJudgmentPOItem.setQueried(true);
    }

    public TaoBaoPO handleMoxieTaoBao(PersonReport report) {
        JSONObject taobaoReport = moxieService.fetchTaoBaoReport(report);
        if (taobaoReport == null) {
            return null;
        }
        JSONObject taobaoData = moxieService.fetchTaoBaoOriginalData(report.getMoxieTaskTaobao());

        JSONObject basic_info = taobaoReport.getJSONObject("basic_info");
        JSONObject wealth_info = taobaoReport.getJSONObject("wealth_info");
        JSONObject user_and_account_basic_info = basic_info.getJSONObject("user_and_account_basic_info");
        JSONObject totalssets = wealth_info.getJSONObject("totalssets");
        TaoBaoPO taoBaoPO = new TaoBaoPO(user_and_account_basic_info.getString("taobao_name"),
                user_and_account_basic_info.getString("taobao_phone_number"),
                user_and_account_basic_info.getString("alipay_account"),
                user_and_account_basic_info.getString("taobao_vip_level"),
                user_and_account_basic_info.getString("taobao_vip_count"), totalssets.getString("balance"),
                totalssets.getString("yue_e_bao_amt"), totalssets.getString("huai_bei_limit"),
                totalssets.getString("huai_bei_can_use_limit"));
        if (taobaoData == null) {
            taoBaoPO.setTaobaoNickName("淘宝未提供该数据");
            taoBaoPO.setColor(Color.TIMEOUT);
        } else {
            taoBaoPO.setTaobaoNickName(taobaoData.getJSONObject("userinfo").getString("nick"));
            taoBaoPO.setColor(Color.SUCCESS);
        }
        System.out.println(taoBaoPO);

        JSONObject consumption_analysis = taobaoReport.getJSONObject("consumption_analysis");
        JSONObject total_consumption = consumption_analysis.getJSONObject("total_consumption");
        JSONObject total_consum_amt = total_consumption.getJSONObject("total_consum_amt");
        JSONObject total_consum_times = total_consumption.getJSONObject("total_consum_times");
        Set<String> keySet = total_consum_amt.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList, Collections.reverseOrder());
        JSONArray consumeAnsycs = new JSONArray();
        for (String string : keyList) {
            System.out.println(string);
            if ("sum".equals(string)) {
                continue;
            }
            ConsumeAnsyc consumeAnsyc = new ConsumeAnsyc(string, total_consum_amt.getString(string),
                    total_consum_times.getString(string) == null ? "0" : total_consum_times.getString(string));
            consumeAnsycs.add(consumeAnsyc);
            System.out.println(consumeAnsyc);
        }
        taoBaoPO.setConsumeAnsyc(consumeAnsycs);
        if (taobaoData != null) {
            JSONObject orderReport = taobaoData.getJSONObject("tradedetails");
            JSONArray consumeOrders = new JSONArray();
            JSONArray tradedetails = orderReport.getJSONArray("tradedetails");
            for (Object object : tradedetails) {
                JSONObject jsonObject = (JSONObject) object;
                String goodName = "";
                JSONArray sub_orders = jsonObject.getJSONArray("sub_orders");
                for (Object object2 : sub_orders) {
                    JSONObject jsonObject2 = (JSONObject) object2;
                    goodName = goodName + "," + jsonObject2.getString("item_name");
                }
                goodName = goodName.substring(1);
                ConsumeOrder consumeOrder = new ConsumeOrder(
                        DateUtils.getStrByDate(jsonObject.getDate("trade_createtime"), "yyyy-MM-dd HH:mm:ss"), goodName,
                        jsonObject.getBigDecimal("actual_fee").divide(BigDecimal.valueOf(100L)).toString());
                consumeOrders.add(consumeOrder);
                System.out.println(consumeOrder);
            }
            taoBaoPO.setConsumeOrder(consumeOrders);
            taoBaoPO.setColor(Color.SUCCESS);
        } else {
            taoBaoPO.setColor(Color.TIMEOUT);
        }

        JSONArray deliverAddresses = new JSONArray();
        if (taobaoData == null) {
            taoBaoPO.setColor(Color.DANGER);
        } else {
            JSONArray addressArray = taobaoData.getJSONArray("deliveraddress");
            for (Object object : addressArray) {
                JSONObject jsonObject = (JSONObject) object;
                DeliverAddress deliverAddress = new DeliverAddress(jsonObject.getString("name"),
                        jsonObject.getString("phone_no"), jsonObject.getString("zip_code"),
                        jsonObject.getString("address"), jsonObject.getBoolean("default").toString());
                deliverAddresses.add(deliverAddress);
                System.out.println(deliverAddress);
            }
            taoBaoPO.setDeliverAddress(deliverAddresses);
            taoBaoPO.setColor(Color.SUCCESS);
        }
        taoBaoPO.setDeliverAddress(deliverAddresses);
        return taoBaoPO;
    }


    public CarrierPO handleMoxieCarrier(PersonReport report) {
        JSONObject moxieReport = moxieService.fetchCarrierReport(report);
        if (moxieReport == null) {
            return null;
        }
        CarrierPO carrierPO = new CarrierPO(Color.SUCCESS);
        JSONArray cellphoneArray = moxieReport.getJSONArray("cell_phone");
        for (Object o : cellphoneArray) {
            JSONObject cellphoneItem = (JSONObject) o;
            if ("reliability".equals(cellphoneItem.getString("key"))) {
                String reliability = cellphoneItem.getString("value");
                Color color = "实名认证".equals(reliability) ? Color.SUCCESS : Color.DANGER;
                carrierPO.setColor(color);
                carrierPO.setReliability(reliability);
            } else if ("live_address".equals(cellphoneItem.getString("key"))) {
                carrierPO.setLiveAddress(cellphoneItem.getString("value"));
            } else if ("in_time".equals(cellphoneItem.getString("key"))) {
                Integer inTime = Integer.valueOf(cellphoneItem.getString("value"));
                Color color;
                String content;
                if (inTime > 12) {
                    color = Color.SUCCESS;
                } else if (inTime > 6) {
                    color = Color.ATTENTION;
                } else {
                    color = Color.WARNING;
                }
                carrierPO.setColor(color);
                carrierPO.setInTime(inTime);
            } else if ("package_name".equals(cellphoneItem.getString("key"))) {
                carrierPO.setPackageName(cellphoneItem.getString("value"));
            }
        }
        JSONArray basicCheckItemsArray = moxieReport.getJSONArray("basic_check_items");
        for (Object o : basicCheckItemsArray) {
            JSONObject basicCheckItem = (JSONObject) o;
            if ("name_match".equals(basicCheckItem.getString("check_item"))) {
                String nameMatch = basicCheckItem.getString("result");
                Color color = "匹配成功".equals(nameMatch) ? Color.SUCCESS : Color.DANGER;
                carrierPO.setColor(color);
                carrierPO.setNameMatch(nameMatch);
            }
        }
        JSONArray activeDegreeArray = moxieReport.getJSONArray("active_degree");
        final DecimalFormat df = new DecimalFormat("#.0");
        for (Object o : activeDegreeArray) {
            JSONObject activeDegreeItem = (JSONObject) o;
            if ("net_used".equals(activeDegreeItem.getString("app_point"))) {
                JSONObject item = activeDegreeItem.getJSONObject("item");
                carrierPO.setNetUsedPerMonth((int) (item.getFloat("avg_item_6m") / 1024));
            } else if ("call_cnt".equals(activeDegreeItem.getString("app_point"))) {
                JSONObject item = activeDegreeItem.getJSONObject("item");
                String callCountPerDayStr = df.format(item.getFloat("avg_item_6m") / 30);
                carrierPO.setCallCountPerDay(Double.parseDouble(callCountPerDayStr));
            } else if ("avg_call_time".equals(activeDegreeItem.getString("app_point"))) {
                JSONObject item = activeDegreeItem.getJSONObject("item");
                carrierPO.setCallTimePerTime(item.getLong("item_6m").intValue());
            }
        }
        JSONArray consumptionDetailArray = moxieReport.getJSONArray("consumption_detail");
        for (Object o : consumptionDetailArray) {
            JSONObject consumptionDetailItem = (JSONObject) o;
            if ("total_fee".equals(consumptionDetailItem.getString("app_point"))) {
                JSONObject item = consumptionDetailItem.getJSONObject("item");
                String moneyPerMonthStr = df.format(item.getFloat("avg_item_6m") / 100);
                carrierPO.setMoneyPerMonth(Double.parseDouble(moneyPerMonthStr));
            }
        }
        // TODO: 2018/1/22 risks
        // 行为检测 behavior_check
        JSONArray behaviorCheckArray = moxieReport.getJSONArray("behavior_check");
        for (Object o : behaviorCheckArray) {
            JSONObject behaviorCheckItem = (JSONObject) o;
            if ("contact_collection".equals(behaviorCheckItem.getString("check_point"))) {
                carrierPO.setContactCollection(getBehaviorResult(behaviorCheckItem));
                carrierPO.setContactCollectionEvidences(getBehaviorEvidence(behaviorCheckItem));
            } else if ("contact_loan".equals(behaviorCheckItem.getString("check_point"))) {
                carrierPO.setContactLoan(getBehaviorResult(behaviorCheckItem));
                carrierPO.setContactLoanEvidences(getBehaviorEvidence(behaviorCheckItem));
            } else if ("contact_credit_card".equals(behaviorCheckItem.getString("check_point"))) {
                carrierPO.setContactCreditCard(getBehaviorResult(behaviorCheckItem));
                carrierPO.setContactCreditCardEvidences(getBehaviorEvidence(behaviorCheckItem));
            } else if ("contact_bank".equals(behaviorCheckItem.getString("check_point"))) {
                carrierPO.setContactBank(getBehaviorResult(behaviorCheckItem));
                carrierPO.setContactBankEvidences(getBehaviorEvidence(behaviorCheckItem));
            } else if ("phone_call".equals(behaviorCheckItem.getString("check_point"))) {
                carrierPO.setPhoneCall(getBehaviorResult(behaviorCheckItem));
                carrierPO.setPhoneCallEvidences(getBehaviorEvidence(behaviorCheckItem));
            } else if ("regular_circle".equals(behaviorCheckItem.getString("check_point"))) {
                carrierPO.setRegularCircle(behaviorCheckItem.getString("result"));
                carrierPO.setRegularCircleEvidence(behaviorCheckItem.getString("evidence"));
            } else if ("phone_silent".equals(behaviorCheckItem.getString("check_point"))) {
                carrierPO.setPhoneSilent(behaviorCheckItem.getString("result"));
                carrierPO.setPhoneSilentEvidence(behaviorCheckItem.getString("evidence"));
            } else if ("contact_each_other".equals(behaviorCheckItem.getString("check_point"))) {
                carrierPO.setContactEachOther(behaviorCheckItem.getString("result"));
                carrierPO.setContactEachOtherEvidence(behaviorCheckItem.getString("evidence"));
            }
        }

        // 用户信息监测 user_info_check
        JSONArray userInfoCheckArray = moxieReport.getJSONArray("user_info_check");
        for (Object o : userInfoCheckArray) {
            JSONObject userInfoCheckItem = (JSONObject) o;
            JSONObject checkSearchInfo = userInfoCheckItem.getJSONObject("check_search_info");
            carrierPO.setSearchedOrganizationCount(checkSearchInfo.getInteger("searched_org_cnt"));
            JSONArray jsonArray = checkSearchInfo.getJSONArray("searched_org_type");
            List<String> organizationTypes = new ArrayList<>();
            for (Object object2 : jsonArray) {
                if ("CASH_LOAN".equals(object2.toString())) {
                    organizationTypes.add("现金贷");
                } else if ("CUSTOMER_FINANCE".equals(object2.toString())) {
                    organizationTypes.add("消费金融");
                } else if ("ZHENGXIN".equals(object2.toString())) {
                    organizationTypes.add("征信");
                } else if ("DIVERSION".equals(object2.toString())) {
                    organizationTypes.add("娱乐");
                } else if ("DATA_PLATFORM".equals(object2.toString())) {
                    organizationTypes.add("数据平台");
                }

            }
            carrierPO.setSearchedOrganizationType(JSON.toJSONString(organizationTypes));
            JSONObject checkBlackInfo = userInfoCheckItem.getJSONObject("check_black_info");
            carrierPO.setContactsClass1BlacklistCnt(checkBlackInfo.getInteger("contacts_class1_blacklist_cnt"));
            carrierPO.setContactsClass2BlacklistCnt(checkBlackInfo.getInteger("contacts_class2_blacklist_cnt"));
        }
        // 通话详单（近6月）call_contact_detail
        JSONArray callContactDetail = moxieReport.getJSONArray("call_contact_detail");
        JSONArray callList = new JSONArray();
        callContactDetail.stream().limit(100).forEachOrdered(row -> {
            JSONObject jsonObject = (JSONObject) row;
            CallListItem item = new CallListItem(jsonObject.getString("city"), jsonObject.getString("peer_num"),
                    jsonObject.getString("company_name"), jsonObject.getLong("call_time_6m").intValue(),
                    jsonObject.getLong("dial_cnt_6m").intValue(), jsonObject.getLong("dialed_cnt_6m").intValue());
            callList.add(item);
        });
        carrierPO.setCallList(callList);

        // 信息核对 application_check
        JSONArray applicationCheckArray = moxieReport.getJSONArray("application_check");
        JSONArray linkmanChecks = new JSONArray();
        if (applicationCheckArray != null) {
            applicationCheckArray.stream().forEach(row -> {
                JSONObject checkPoints = ((JSONObject) row).getJSONObject("check_points");
                LinkmanCheck linkmanCheck = new LinkmanCheck(checkPoints.getString("relationship"), checkPoints.getString("key_value"),
                        checkPoints.getString("contact_name"), checkPoints.getString("check_mobile"),
                        checkPoints.getString("check_xiaohao"));
                linkmanChecks.add(linkmanCheck);
            });
            carrierPO.setLinkmanChecks(linkmanChecks);
        }
        return carrierPO;
    }


    public Boolean handleAuthMobile(String name, String idCard, String mobile) {
        Map result = fetchService.fetchAuthMobile(name, idCard, mobile);
        if (result != null && "0000".equals(result.get("rc"))) {
            return "T".equals(((HashMap) result.get("data")).get("result"));
        }
        return null;
    }

    private String getBehaviorResult(JSONObject behaviorCheckItem) {
        return behaviorCheckItem.getString("result");
    }

    private JSONArray getBehaviorEvidence(JSONObject behaviorCheckItem) {
        String result = behaviorCheckItem.getString("result");
        String evidence = behaviorCheckItem.getString("evidence");
        if (result.contains("无")) {
            return new JSONArray();
        }
        String[] split = evidence.split("，");
        List<String> rows = new ArrayList<>();
        for (String aSplit : split) {
            if (aSplit.startsWith("联系列表：")) {
                rows.add(aSplit.substring(5));
            } else {
                rows.add(aSplit);
            }
        }
        return JSONArray.parseArray(JSON.toJSONString(rows));
    }

    private static int compareConcludeTime(Object row1, Object row2) {
        long time1 = Long.valueOf(((HashMap) row1).get("concludeTime").toString());
        long time2 = Long.valueOf(((HashMap) row2).get("concludeTime").toString());
        return (int) (time2 - time1);
    }

    private String convertCollegeType(String collegeType) {
        switch (collegeType) {
            case "1":
                return "985";
            case "2":
                return "211";
            default:
                return "非985,非211";
        }
    }

    private String convertRiskCode(int riskCode, int riskCodeValue) {
        String content;
        switch (riskCode) {
            case 1:
                content = "信贷中介";
                break;
            case 2:
                content = "不法分子";
                break;
            case 3:
                content = "虚假资料";
                break;
            case 4:
                content = "羊毛党";
                break;
            case 5:
                content = "身份认证失败";
                break;
            case 6:
                content = "疑似恶意欺诈";
                break;
            case 7:
                content = "失信名单";
                break;
            case 8:
                content = "异常支付行为";
                break;
            default:
                return null;
        }
        switch (riskCodeValue) {
            case 1:
                content += ":低风险";
                break;
            case 2:
                content += ":中风险";
                break;
            case 3:
                content += ":高风险";
                break;
            default:
                break;
        }
        return content;
    }

    private Color convertRiskCodeValue(int riskCodeValue) {
        switch (riskCodeValue) {
            case 1:
                return Color.ATTENTION;
            case 2:
                return Color.WARNING;
            case 3:
                return Color.DANGER;
            default:
                return Color.TIMEOUT;
        }
    }

    static HashMap<String, RiskItem> verifyTable;

    static {
        verifyTable = new HashMap<>();
        verifyTable.put("V_CN_NA", new RiskItem("查询不到身份证信息", Color.WARNING));
        verifyTable.put("V_CN_NM_UM", new RiskItem("姓名与身份证不匹配", Color.DANGER));
        verifyTable.put("V_CN_NM_MA", new RiskItem("姓名与身份证匹配", Color.SUCCESS));

        verifyTable.put("V_PH_NA", new RiskItem("查询不到电话号码信息", Color.WARNING));
        verifyTable.put("V_PH_CN_UM", new RiskItem("电话号码与身份证不匹配", Color.DANGER));
        verifyTable.put("V_PH_CN_MA_UL30D", new RiskItem("电话号码与身份证匹配,1个月内有使用", Color.SUCCESS));
        verifyTable.put("V_PH_CN_MA_UL90D", new RiskItem("电话号码与身份证匹配,1个月内没有使用,3个月内有使用", Color.ATTENTION));
        verifyTable.put("V_PH_CN_MA_UL180D", new RiskItem("电话号码与身份证匹配,3个月内没有使用,6个月内有使用", Color.WARNING));
        verifyTable.put("V_PH_CN_MA_UM180D", new RiskItem("电话号码与身份证匹配,6个月内没有使用", Color.DANGER));

        verifyTable.put("V_PH_NM_UM", new RiskItem("电话号码与姓名不匹配", Color.DANGER));
        verifyTable.put("V_PH_NM_MA_UL30D", new RiskItem("电话号码与姓名匹配,1个月内有使用", Color.SUCCESS));
        verifyTable.put("V_PH_NM_MA_UL90D", new RiskItem("电话号码与姓名匹配,1个月内没有使用,3个月内有使用", Color.ATTENTION));
        verifyTable.put("V_PH_NM_MA_UL180D", new RiskItem("电话号码与姓名匹配,3个月内没有使用,6个月内有使用", Color.WARNING));
        verifyTable.put("V_PH_NM_MA_UM180D", new RiskItem("电话号码与姓名匹配,6个月内没有使用", Color.DANGER));

        verifyTable.put("V_BC_CN_UK", new RiskItem("银行卡与身份证关系未知", Color.ATTENTION));
        verifyTable.put("V_BC_CN_UM", new RiskItem("银行卡与身份证不匹配", Color.DANGER));
        verifyTable.put("V_BC_CN_MA_UL180D", new RiskItem("银行卡与身份证匹配,6个月内有使用", Color.SUCCESS));
        verifyTable.put("V_BC_CN_MA_UL360D", new RiskItem("银行卡与身份证匹配,6个月内没有使用,12个月内有使用", Color.WARNING));
        verifyTable.put("V_BC_CN_MA_UM360D", new RiskItem("银行卡与身份证匹配,12个月内没有使用", Color.DANGER));

        verifyTable.put("V_BC_PH_UK", new RiskItem("银行卡与手机号关系未知", Color.ATTENTION));
        verifyTable.put("V_BC_PH_UM", new RiskItem("银行卡与手机号不匹配", Color.DANGER));
        verifyTable.put("V_BC_PH_MA_UL180D", new RiskItem("银行卡与手机号匹配,6个月内有使用", Color.SUCCESS));
        verifyTable.put("V_BC_PH_MA_UL360D", new RiskItem("银行卡与手机号匹配,6个月内没有使用,12个月内有使用", Color.WARNING));
        verifyTable.put("V_BC_PH_MA_UM360D", new RiskItem("银行卡与手机号匹配,12个月内没有使用", Color.DANGER));

        verifyTable.put("V_AD_CN_UK", new RiskItem("地址与身份证关系未知", Color.ATTENTION));
        verifyTable.put("V_AD_CN_UM", new RiskItem("地址与身份证不匹配", Color.DANGER));
        verifyTable.put("V_AD_CN_MA_UL180D", new RiskItem("地址与身份证匹配,6个月内有使用", Color.SUCCESS));
        verifyTable.put("V_AD_CN_MA_UL360D", new RiskItem("地址与身份证匹配,6个月内没有使用,12个月内有使用", Color.WARNING));
        verifyTable.put("V_AD_CN_MA_UM360D", new RiskItem("地址与身份证匹配,12个月内没有使用", Color.DANGER));

        verifyTable.put("V_AD_PH_UK", new RiskItem("地址与手机号关系未知", Color.ATTENTION));
        verifyTable.put("V_AD_PH_UM", new RiskItem("地址与手机号不匹配", Color.DANGER));
        verifyTable.put("V_AD_PH_MA_UL180D", new RiskItem("地址与手机号匹配,6个月内有使用", Color.SUCCESS));
        verifyTable.put("V_AD_PH_MA_UL360D", new RiskItem("地址与手机号匹配,6个月内没有使用,12个月内有使用", Color.WARNING));
        verifyTable.put("V_AD_PH_MA_UM360D", new RiskItem("地址与手机号匹配,12个月内没有使用", Color.DANGER));

    }

}
