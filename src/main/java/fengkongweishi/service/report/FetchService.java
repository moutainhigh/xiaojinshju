package fengkongweishi.service.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fengkongweishi.entity.apisearchlog.APISearchLog;
import fengkongweishi.entity.apisearchlog.APISearchLogRepository;
import fengkongweishi.entity.supplyapi.*;
import fengkongweishi.service.ParameterService;
import fengkongweishi.util.AliyunCallable;
import fengkongweishi.util.CsecCallable;
import fengkongweishi.util.XinShuCallable;
import fengkongweishi.util.YoufenCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * api调用服务
 *
 * @author huanghengkun
 * @date 2018/01/10
 */
@Service
public class FetchService {
    private static Logger logger = LoggerFactory.getLogger(FetchService.class);

    @Autowired
    ParameterService parameterService;
    @Autowired
    APISearchLogRepository apiSearchLogRepository;
    @Autowired
    ZhimaService zhimaService;
    @Autowired
    AliYunIdCard aliYunIdCard;
    @Autowired
    AliYunMobile aliYunMobile;
    @Autowired
    AliYunBankCard aliYunBankCard;
    @Autowired
    AliYunViolationXK aliYunViolationXK;
    @Autowired
    AliYunViolationWS aliYunViolationWS;
    @Autowired
    AliYunOcrVehicle aliYunOcrVehicle;
    @Autowired
    AliYunDishonestBlack aliYunDishonestBlack;
    @Autowired
    YouFenCriminal youFenCriminal;
    @Autowired
    YouFenMultipleHeadLend youFenMultipleHeadLend;
    @Autowired
    YouFenCourtJudgment youFenCourtJudgment;
    @Autowired
    YouFenCourtJudgmentDetail youFenCourtJudgmentDetail;
    @Autowired
    YouFenEducation youFenEducation;
    @Autowired
    AlipayZhimaScore alipayZhimaScore;
    @Autowired
    AlipayWatchList alipayWatchList;
    @Autowired
    CsecAntiFraud csecAntiFraud;
    @Autowired
    AlipayAntiFraud alipayAntiFraud;
    @Autowired
    XinShuRiskA xinShuRiskA;
    @Autowired
    XinShuRiskB xinShuRiskB;
    @Autowired
    XinShuAuthMobile xinShuAuthMobile;
    @Autowired
    XinShuCourt xinShuCourt;

    public Map fetchIdCard(String idCard) {
        API api = new API();
        api.setSupplyAPI(aliYunIdCard);
        api.getParameters().put("idcard", idCard);

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询idCard");
        } else {
            logger.info("即时查询idCard");
            AliyunCallable aliyunCallable = new AliyunCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = aliyunCallable.call();
            if (cacheData != null && "0".equals(cacheData.get("status").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchMobile(String mobile) {
        API api = new API();
        api.setSupplyAPI(aliYunMobile);
        api.getParameters().put("mobile", mobile);
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询Cellphone");
        } else {
            logger.info("即时查询Cellphone");
            AliyunCallable aliyunCallable = new AliyunCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = aliyunCallable.call();
            if (cacheData != null && "0".equals(cacheData.get("error_code").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchDishonestBlacklist(String name, String idCard) {
        API api = new API();
        api.setSupplyAPI(aliYunDishonestBlack);
        api.getParameters().put("name", name);
        api.getParameters().put("cardno", idCard);
        api.getParameters().put("type", "person");

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询DishonestBlacklist");
        } else {
            logger.info("即时查询DishonestBlacklist");
            AliyunCallable aliyunCallable = new AliyunCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = aliyunCallable.call();
            if (cacheData != null && "0".equals(cacheData.get("code").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchBankCard(String bankcard) {
        API api = new API();
        api.setSupplyAPI(aliYunBankCard);
        api.getParameters().put("bankcard", bankcard);
        // 版本号
        api.getParameters().put("apiversion", "2.0.5");
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询BankCard");
        } else {
            logger.info("即时查询BankCard");
            AliyunCallable aliyunCallable = new AliyunCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = aliyunCallable.call();
            if (cacheData != null && "0".equals(cacheData.get("error_code").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchViolationXK(String plateNumber, String vin, String engineNo, String carType) {
        API api = new API();
        api.setSupplyAPI(aliYunViolationXK);
        api.getParameters().put("plateNumber", plateNumber);
        api.getParameters().put("vin", vin);
        api.getParameters().put("engineNo", engineNo);
        api.getParameters().put("carType", carType);
        api.getHeaders().put("Content-Type", "application/json; charset=UTF-8");
        String body = "{\"plateNumber\":\"" + plateNumber + "\",\"vin\":\"" + vin + "\",\"engineNo\":\"" + engineNo
                + "\",\"carType\":\"" + carType + "\"}";

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询ViolationXK");
        } else {
            logger.info("即时查询ViolationXK");
            AliyunCallable aliyunCallable = new AliyunCallable(api.getSupplyAPI(), api.getHeaders(), new HashMap<>(),
                    body);
            cacheData = aliyunCallable.call();
            if (cacheData != null && "true".equals(cacheData.get("success").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchViolationWS(String plateNumber, String vin, String engineNo, String carType, String mobile) {
        API api = new API();
        api.setSupplyAPI(aliYunViolationWS);
        api.getParameters().put("lsprefix", plateNumber.substring(0, 1));
        api.getParameters().put("lsnum", plateNumber.substring(1));
        api.getParameters().put("engineno", engineNo);
        api.getParameters().put("frameno", vin);
        api.getParameters().put("lstype", carType);
        api.getParameters().put("mobile", mobile);
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询ViolationWS");
        } else {
            logger.info("即时查询ViolationWS");
            AliyunCallable aliyunCallable = new AliyunCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = aliyunCallable.call();
            if (cacheData != null && "0".equals(cacheData.get("status").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchOcrVehicle(String base64Code) {
        API api = new API();
        api.setSupplyAPI(aliYunOcrVehicle);
        api.getHeaders().put("Content-Type", "application/json; charset=UTF-8");
        String body = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\"" + base64Code + "\"}}]}";
        logger.info("即时查询OcrVehicle");
        AliyunCallable aliyunCallable = new AliyunCallable(api.getSupplyAPI(), api.getHeaders(), new HashMap<>(), body);
        return aliyunCallable.call();
    }

    public Map fetchCriminal(String name, String idCard) {
        API api = new API();
        api.setSupplyAPI(youFenCriminal);
        api.getParameters().put("name", name);
        api.getParameters().put("idcard", idCard);
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询Criminal");
        } else {
            logger.info("即时查询Criminal");
            YoufenCallable youfenCallable = new YoufenCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    new HashMap<>());
            cacheData = youfenCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("resCode").toString())
                    && "2012".equals(((HashMap) cacheData.get("data")).get("statusCode").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchEducation(String name, String idCrad) {
        API api = new API();
        api.setSupplyAPI(youFenEducation);
        api.getParameters().put("name", name);
        api.getParameters().put("idcard", idCrad);
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询Education");
        } else {
            logger.info("即时查询Education");
            YoufenCallable youfenCallable = new YoufenCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    new HashMap<>());
            cacheData = youfenCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("resCode").toString())
                    && "2012".equals(((HashMap) cacheData.get("data")).get("statusCode").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchMultipleHeadLend(String mobile) {
        API api = new API();
        api.setSupplyAPI(youFenMultipleHeadLend);
        api.getParameters().put("cycle", "12");
        api.getParameters().put("cellphone", mobile);
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询MultipleHeadLend");
        } else {
            logger.info("即时查询MultipleHeadLend");
            YoufenCallable youfenCallable = new YoufenCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    new HashMap<>());
            cacheData = youfenCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("resCode").toString())
                    && "2012".equals(((HashMap) cacheData.get("data")).get("statusCode").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchCourtJudgment(String name, String idCard) {
        API api = new API();
        api.setSupplyAPI(youFenCourtJudgment);
        api.getParameters().put("name", name);
        api.getParameters().put("idcard", idCard);
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询CourtJudgment");
        } else {
            logger.info("即时查询CourtJudgment");
            YoufenCallable youfenCallable = new YoufenCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    new HashMap<>());
            cacheData = youfenCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("resCode").toString())
                    && "2012".equals(((HashMap) cacheData.get("data")).get("statusCode").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchCourtJudgmentDetail(String docId) {
        API api = new API();
        api.setSupplyAPI(youFenCourtJudgmentDetail);
        api.getParameters().put("docId", docId);
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询CourtJudgmentDetail");
        } else {
            logger.info("即时查询CourtJudgmentDetail");
            YoufenCallable youfenCallable = new YoufenCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    new HashMap<>());
            cacheData = youfenCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("resCode").toString())
                    && "2012".equals(((HashMap) cacheData.get("data")).get("statusCode").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchZhimaScore(String name, String idCard) {
        API api = new API();
        api.setSupplyAPI(alipayZhimaScore);
        api.getParameters().put("idcard", idCard);
        api.getParameters().put("name", name);

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询zhimascore");
        } else {
            logger.info("即时查询zhimascore");
            cacheData = zhimaService.zhimaScore(api);
            System.out.println(cacheData);
            if (cacheData != null) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchWatchlist(String name, String idCard) {
        API api = new API();
        api.setSupplyAPI(alipayWatchList);
        api.getParameters().put("name", name);
        api.getParameters().put("certNo", idCard);

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询watchlist");
        } else {
            logger.info("即时查询watchlist");
            cacheData = zhimaService.watchlist(api);
            if (cacheData != null && "10000".equals(
                    ((HashMap) cacheData.get("zhima_credit_watchlist_brief_get_response")).get("code").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchAlipayAntiFraud(String name, String idCard, String mobile, String bankCard, String address) {
        API api = new API();
        api.setSupplyAPI(alipayAntiFraud);
        api.getParameters().put("name", name);
        api.getParameters().put("certNo", idCard);
        api.getParameters().put("mobile", mobile);
        api.getParameters().put("bankCard", bankCard);
        api.getParameters().put("address", address);

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询AlipayAntiFraud");
        } else {
            logger.info("即时查询AlipayAntiFraud");
            cacheData = zhimaService.antifraud(api);
            if (cacheData != null) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchCsecAntiFraud(String idCard, String mobile, String name) {
        API api = new API();
        api.setSupplyAPI(csecAntiFraud);
        // 基本字段
        api.getParameters().put("idNumber", idCard);
        api.getParameters().put("phoneNumber", "0086-" + mobile);
        // 可选字段
        if (name != null) {
            api.getParameters().put("name", name);
        }
        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询CsecAntiFraud");
        } else {
            logger.info("即时查询CsecAntiFraud");
            CsecCallable csecCallable = new CsecCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    new HashMap<>());
            cacheData = csecCallable.call();
            if (cacheData != null && "0".equals(cacheData.get("code").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchXinShuRiskA(String name, String idCard, String mobile, String bankCard) {
        API api = new API();
        api.setSupplyAPI(xinShuRiskA);
        api.getParameters().put("name", name);
        api.getParameters().put("idCard", idCard);
        api.getParameters().put("mobile", mobile);
        api.getParameters().put("bankCardNo", bankCard);

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询XinShuRiskA");
        } else {
            logger.info("即时查询XinShuRiskA");
            XinShuCallable xinShuCallable = new XinShuCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = xinShuCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("rc").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchXinShuRiskB(String name, String idCard, String mobile, String bankCard) {
        API api = new API();
        api.setSupplyAPI(xinShuRiskB);
        api.getParameters().put("name", name);
        api.getParameters().put("idCard", idCard);
        api.getParameters().put("mobile", mobile);
        api.getParameters().put("bankCardNo", bankCard);

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询XinShuRiskB");
        } else {
            logger.info("即时查询XinShuRiskB");
            XinShuCallable xinShuCallable = new XinShuCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = xinShuCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("rc").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchAuthMobile(String name, String idCard, String mobile) {
        API api = new API();
        api.setSupplyAPI(xinShuAuthMobile);
        api.getParameters().put("name", name);
        api.getParameters().put("idCard", idCard);
        api.getParameters().put("mobile", mobile);

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询AuthMobile");
        } else {
            logger.info("即时查询AuthMobile");
            XinShuCallable xinShuCallable = new XinShuCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = xinShuCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("rc").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchXinShuCourtSimple(String q, String datatype) {
        API api = new API();
        api.setSupplyAPI(xinShuCourt);
        api.getParameters().put("q", q);
        api.getParameters().put("datatype", datatype);
        api.getParameters().put("pageno", "1");
        api.getParameters().put("range", "100");

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询XinShuCourtSimple");
        } else {
            logger.info("即时查询XinShuCourtSimple");
            XinShuCallable xinShuCallable = new XinShuCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = xinShuCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("rc").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public Map fetchXinShuCourtDetail(String id, String datatype) {
        API api = new API();
        api.setSupplyAPI(xinShuCourt);
        api.getParameters().put("id", id);
        api.getParameters().put("datatype", datatype);

        Map cacheData = getCache(api);
        if (cacheData != null) {
            logger.info("缓存查询XinShuCourtDetail");
        } else {
            logger.info("即时查询XinShuCourtDetail");
            XinShuCallable xinShuCallable = new XinShuCallable(api.getSupplyAPI(), new HashMap<>(), api.getParameters(),
                    "");
            cacheData = xinShuCallable.call();
            if (cacheData != null && "0000".equals(cacheData.get("rc").toString())) {
                putCache(api, cacheData);
            }
        }
        return cacheData;
    }

    public static class API {
        private ISupplyAPI supplyAPI;
        private HashMap<String, String> parameters = new HashMap<>();
        private HashMap<String, String> headers = new HashMap<>();

        public ISupplyAPI getSupplyAPI() {
            return supplyAPI;
        }

        public void setSupplyAPI(ISupplyAPI supplyAPI) {
            this.supplyAPI = supplyAPI;
        }

        public HashMap<String, String> getParameters() {
            return parameters;
        }

        public void setParameters(HashMap<String, String> parameters) {
            this.parameters = parameters;
        }

        public HashMap<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(HashMap<String, String> headers) {
            this.headers = headers;
        }

    }

    private String parametersToString(HashMap<String, String> value) {
        return parameterService.createSign(value, "");
    }

    private void putCache(API api, Object map) {
        APISearchLog apiSearchLog = new APISearchLog();
        apiSearchLog.setApicode(api.getSupplyAPI().getCode());
        apiSearchLog.setCreateTime(new Date());
        apiSearchLog.setParameters(parametersToString(api.getParameters()));

        ObjectMapper mapper = new ObjectMapper();
        String writeValueAsString;
        try {
            writeValueAsString = mapper.writeValueAsString(map);
            apiSearchLog.setResult(writeValueAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        apiSearchLogRepository.save(apiSearchLog);

    }

    private Map getCache(API api) {
        String parameters = parametersToString(api.getParameters());
        APISearchLog ret = apiSearchLogRepository
                .findFirstByApicodeAndParametersOrderByCreateTimeDesc(api.getSupplyAPI().getCode(), parameters);
        if (ret != null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ret.getCreateTime());
            calendar.add(Calendar.SECOND, api.getSupplyAPI().getEffectiveTime());
            Calendar calendarNow = Calendar.getInstance();
            if (calendar.compareTo(calendarNow) < 0) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            Map bean = null;
            try {
                bean = mapper.readValue(ret.getResult(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bean;
        } else {
            return null;
        }
    }

}
