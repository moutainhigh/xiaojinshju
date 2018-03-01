package com.fengkongweishi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.po.EducationPO;
import fengkongweishi.entity.personreport.po.IdCardPO;
import fengkongweishi.entity.personreport.po.ZhimaScorePO;
import fengkongweishi.enums.Color;
import fengkongweishi.service.report.AnalyseService;
import fengkongweishi.service.report.FetchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.web.ServletTestExecutionListener;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author huanghengkun
 * @date 2018/01/24
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)*/
@TestExecutionListeners(listeners = {SpringBootDependencyInjectionTestExecutionListener.class, ServletTestExecutionListener.class})
public class AnalyseServiceMockTest {
    @InjectMocks
    private AnalyseService analyseService;
    @Mock
    private FetchService fetchService;

    private String xinshuRiskA = "{orderNo=201801261557165108760, data={totalCounts=[{blackType=A, blackCount=0}, {blackType=B, blackCount=0}, {blackType=C, blackCount=0}, {blackType=D, blackCount=0}, {blackType=E, blackCount=3}], list=[{blackRiskType=E, blackFactsType=E02, blackFacts=2家P2P网贷, blackAmt=, blackHappenDate=, blackDurationTime=, blackPublishSource=}, {blackRiskType=E, blackFactsType=E03, blackFacts=6家一般消费分期平台，5家小额贷款公司，12家P2P网贷，1家大型消费金融公司，1家银行小微贷款, blackAmt=, blackHappenDate=, blackDurationTime=, blackPublishSource=}, {blackRiskType=E, blackFactsType=, blackFacts=1家P2P网贷, blackAmt=, blackHappenDate=, blackDurationTime=, blackPublishSource=}]}, rc=0000, msg=查询成功}";

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
    }

    //////////////////////////////idcard begin
    @Test
    public void testMockAsyncHandleIdCard() throws IOException, ExecutionException, InterruptedException {
        final String mockIdcardStr = "{\"status\":\"0\",\"msg\":\"ok\",\"result\":{\"province\":\"浙江\",\"city\":\"台州\",\"town\":\"玉环县\",\"area\":\"浙江省 台州市 玉环县\",\"lastflag\":\"0\",\"sex\":\"男\",\"birth\":\"1992年12月26日\"}}";
        Map mockIdcard = new ObjectMapper().readValue(mockIdcardStr, Map.class);
        when(fetchService.fetchIdCard("331021199212261270")).thenReturn(mockIdcard);
        final String mockMobileStr = "{\"error_code\":0,\"reason\":\"Success\",\"result\":{\"company\":\"中国移动\",\"card\":\"中国移动 GSM\",\"province\":\"浙江\",\"city\":\"杭州\",\"num\":\"1826885\",\"citycode\":\"0571\",\"areacode\":\"330100\",\"provincecode\":\"330000\",\"zip\":\"310000\",\"prefix\":\"182\"}}";
        Map mockMobile = new ObjectMapper().readValue(mockMobileStr, Map.class);
        when(fetchService.fetchMobile("18268850390")).thenReturn(mockMobile);
        final String mockAntiFraudStr = "{\"verifyCode\":[\"V_CN_NM_MA\",\"V_PH_CN_MA_UL90D\",\"V_AD_CN_UK\",\"V_BC_CN_MA_UL180D\"]}";
        Map mockAntiFraud = new ObjectMapper().readValue(mockAntiFraudStr, Map.class);
        when(fetchService.fetchAlipayAntiFraud("黄恒坤", "331021199212261270", "18268850390", "", "")).thenReturn(mockAntiFraud);
        final String mockBankCardStr = "{\"error_code\":0,\"reason\":\"Succes\",\"result\":{\"bankname\":\"杭州商业银行\",\"banknum\":\"4233310\",\"cardprefixnum\":\"623061\",\"cardname\":\"借记IC卡\",\"cardtype\":\"银联借记卡\",\"cardprefixlength\":6,\"isLuhn\":true,\"iscreditcard\":1,\"cardlength\":18,\"province\":\"北京市\",\"city\":\"市辖区\",\"bankurl\":null,\"enbankname\":null,\"abbreviation\":\"HZSYYX\",\"bankimage\":\"\",\"servicephone\":null},\"ordersign\":\"20180127142333079855352569\"}";
        Map mockBankCard = new ObjectMapper().readValue(mockBankCardStr, Map.class);
        when(fetchService.fetchBankCard("")).thenReturn(mockBankCard);

        PersonReport report = new PersonReport();
        report.setName("黄恒坤");
        report.setIdCard("331021199212261270");
        report.setMobile("18268850390");
        report.setBankCard("");
        report.setCommonAddress("");
        Future<Map> mapFuture = analyseService.asyncHandleIdCard(report);
        Map map = mapFuture.get();
        IdCardPO idCardPO = (IdCardPO) map.get("idCardPO");
        assertEquals(Color.ATTENTION, idCardPO.getColor());
    }

    @Test
    public void testMockAsyncHandleIdCard2() throws IOException, ExecutionException, InterruptedException {
        final String mockIdcardStr = "{\"status\":\"0\",\"msg\":\"ok\",\"result\":{\"province\":\"浙江\",\"city\":\"台州\",\"town\":\"玉环县\",\"area\":\"浙江省 台州市 玉环县\",\"lastflag\":\"0\",\"sex\":\"男\",\"birth\":\"1992年12月26日\"}}";
        Map mockIdcard = new ObjectMapper().readValue(mockIdcardStr, Map.class);
        when(fetchService.fetchIdCard("331021199212261270")).thenReturn(mockIdcard);
        final String mockMobileStr = "{\"error_code\":0,\"reason\":\"Success\",\"result\":{\"company\":\"中国移动\",\"card\":\"中国移动 GSM\",\"province\":\"浙江\",\"city\":\"杭州\",\"num\":\"1826885\",\"citycode\":\"0571\",\"areacode\":\"330100\",\"provincecode\":\"330000\",\"zip\":\"310000\",\"prefix\":\"182\"}}";
        Map mockMobile = new ObjectMapper().readValue(mockMobileStr, Map.class);
        when(fetchService.fetchMobile("18268850390")).thenReturn(mockMobile);
        final String mockAntiFraudStr = "{\"verifyCode\":[\"V_CN_NM_UM\",\"V_PH_CN_MA_UL90D\",\"V_AD_CN_MA_UL360D\",\"V_BC_CN_UM\"]}";
        Map mockAntiFraud = new ObjectMapper().readValue(mockAntiFraudStr, Map.class);
        when(fetchService.fetchAlipayAntiFraud("黄恒坤", "331021199212261270", "18268850390", "", "")).thenReturn(mockAntiFraud);
        final String mockBankCardStr = "{\"error_code\":0,\"reason\":\"Succes\",\"result\":{\"bankname\":\"杭州商业银行\",\"banknum\":\"4233310\",\"cardprefixnum\":\"623061\",\"cardname\":\"借记IC卡\",\"cardtype\":\"银联借记卡\",\"cardprefixlength\":6,\"isLuhn\":true,\"iscreditcard\":1,\"cardlength\":18,\"province\":\"北京市\",\"city\":\"市辖区\",\"bankurl\":null,\"enbankname\":null,\"abbreviation\":\"HZSYYX\",\"bankimage\":\"\",\"servicephone\":null},\"ordersign\":\"20180127142333079855352569\"}";
        Map mockBankCard = new ObjectMapper().readValue(mockBankCardStr, Map.class);
        when(fetchService.fetchBankCard("")).thenReturn(mockBankCard);

        PersonReport report = new PersonReport();
        report.setName("黄恒坤");
        report.setIdCard("331021199212261270");
        report.setMobile("18268850390");
        report.setBankCard("");
        report.setCommonAddress("");
        Future<Map> mapFuture = analyseService.asyncHandleIdCard(report);
        Map map = mapFuture.get();
        IdCardPO idCardPO = (IdCardPO) map.get("idCardPO");
        assertEquals(Color.DANGER, idCardPO.getColor());
    }

    //////////////////////////////idcard end
    //////////////////////////////education begin
    @Test
    public void testMockAsyncHandleEducation() throws ExecutionException, InterruptedException, IOException {
        final String mockEducationStr = "{\"resCode\":\"0000\",\"resMsg\":\"提交成功\",\"data\":{\"statusCode\":\"2012\",\"statusMsg\":\"查询成功\",\"result\":{\"name\":\"黄恒坤\",\"college\":\"电子科技大学\",\"degree\":\"本科\",\"startTime\":\"\",\"specialty\":\"\",\"graduateTime\":\"2015\",\"studyResult\":\"毕业\",\"degreeStyle\":\"普通\",\"collegeCity\":\"成都\",\"character\":\"公办\",\"manageDept\":\"教育部\",\"schoolType\":\"工科\",\"collegeLevel\":\"博士\",\"educationApproach\":\"全日制\",\"createDate\":\"1956-01-01\",\"createYears\":\"62\",\"academicianNum\":\"7\",\"postDoctorNum\":\"13\",\"doctorDegreeNum\":\"66\",\"masterDegreeNum\":\"101\",\"nationalSubjectNum\":\"6\",\"studyStyle\":\"\",\"is985\":\"Y\",\"is211\":\"Y\",\"collegeCategory\":\"985、211工程院校\",\"collegeType\":\"1\"}}}";
        Map mockEducation = new ObjectMapper().readValue(mockEducationStr, Map.class);
        when(fetchService.fetchEducation("黄恒坤", "331021199212261270")).thenReturn(mockEducation);
        //Mockito.doReturn(mockEducation).when(fetchService).fetchEducation(Mockito.any(String.class), Mockito.any(String.class));

        PersonReport report = new PersonReport();
        report.setName("黄恒坤");
        report.setIdCard("331021199212261270");
        Future<EducationPO> educationPOFuture = analyseService.asyncHandleEducation(report);
        EducationPO educationPO = educationPOFuture.get();
        verify(fetchService, times(1)).fetchEducation("黄恒坤", "331021199212261270");
        assertEquals("电子科技大学", educationPO.getCollege());
        assertEquals(Color.SUCCESS, educationPO.getColor());
    }

    @Test
    public void testMockNullAsyncHandleEducation() throws ExecutionException, InterruptedException, IOException {
        when(fetchService.fetchEducation(null, null)).thenReturn(null);

        PersonReport report = new PersonReport();
        Future<EducationPO> educationPOFuture = analyseService.asyncHandleEducation(report);
        EducationPO educationPO = educationPOFuture.get();
        verify(fetchService, times(1)).fetchEducation(null, null);
        assertEquals(Color.TIMEOUT, educationPO.getColor());
    }

    @Test
    public void testMockErrorAsyncHandleEducation() throws ExecutionException, InterruptedException, IOException {
        final String mockEducationErrorStr = "{\"resCode\":\"1005\",\"resMsg\":\"name参数为空或格式错误\"}";
        Map mockErrorEducation = new ObjectMapper().readValue(mockEducationErrorStr, Map.class);
        when(fetchService.fetchEducation("", "")).thenReturn(mockErrorEducation);

        PersonReport report = new PersonReport();
        report.setName("");
        report.setIdCard("");
        Future<EducationPO> educationPOFuture = analyseService.asyncHandleEducation(report);
        EducationPO educationPO = educationPOFuture.get();
        verify(fetchService, times(1)).fetchEducation("", "");
        assertEquals(Color.ERROR, educationPO.getColor());
    }

    //////////////////////////////education end
    //////////////////////////////zhimaScire begin
    @Test
    public void testMock400AsyncHandleZhimaScore() throws ExecutionException, InterruptedException, IOException {
        final String mockZhimaScore400Str = "{\"score\":\"400+\"}";
        Map mockZhimaScire400 = new ObjectMapper().readValue(mockZhimaScore400Str, Map.class);
        when(fetchService.fetchZhimaScore("400", "400")).thenReturn(mockZhimaScire400);

        PersonReport report = new PersonReport();
        report.setName("400");
        report.setIdCard("400");
        Future<ZhimaScorePO> zhimaScorePOFuture = analyseService.asyncHandleZhimaScore(report);
        ZhimaScorePO zhimaScorePO = zhimaScorePOFuture.get();
        verify(fetchService, times(1)).fetchZhimaScore("400", "400");
        assertEquals(Color.DANGER, zhimaScorePO.getColor());
    }

    @Test
    public void testMock500AsyncHandleZhimaScore() throws ExecutionException, InterruptedException, IOException {
        final String mockZhimaScore500Str = "{\"score\":\"500+\"}";
        Map mockZhimaScire500 = new ObjectMapper().readValue(mockZhimaScore500Str, Map.class);
        when(fetchService.fetchZhimaScore("500", "500")).thenReturn(mockZhimaScire500);

        PersonReport report = new PersonReport();
        report.setName("500");
        report.setIdCard("500");
        Future<ZhimaScorePO> zhimaScorePOFuture = analyseService.asyncHandleZhimaScore(report);
        ZhimaScorePO zhimaScorePO = zhimaScorePOFuture.get();
        verify(fetchService, times(1)).fetchZhimaScore("500", "500");
        assertEquals(Color.WARNING, zhimaScorePO.getColor());
    }

    @Test
    public void testMock600AsyncHandleZhimaScore() throws ExecutionException, InterruptedException, IOException {
        final String mockZhimaScore600Str = "{\"score\":\"600+\"}";
        Map mockZhimaScire600 = new ObjectMapper().readValue(mockZhimaScore600Str, Map.class);
        when(fetchService.fetchZhimaScore("600", "600")).thenReturn(mockZhimaScire600);

        PersonReport report = new PersonReport();
        report.setName("600");
        report.setIdCard("600");
        Future<ZhimaScorePO> zhimaScorePOFuture = analyseService.asyncHandleZhimaScore(report);
        ZhimaScorePO zhimaScorePO = zhimaScorePOFuture.get();
        verify(fetchService, times(1)).fetchZhimaScore("600", "600");
        assertEquals(Color.ATTENTION, zhimaScorePO.getColor());
    }

    @Test
    public void testMock700AsyncHandleZhimaScore() throws ExecutionException, InterruptedException, IOException {
        final String mockZhimaScore700Str = "{\"score\":\"700+\"}";
        Map mockZhimaScire700 = new ObjectMapper().readValue(mockZhimaScore700Str, Map.class);
        when(fetchService.fetchZhimaScore("700", "700")).thenReturn(mockZhimaScire700);

        PersonReport report = new PersonReport();
        report.setName("700");
        report.setIdCard("700");
        Future<ZhimaScorePO> zhimaScorePOFuture = analyseService.asyncHandleZhimaScore(report);
        ZhimaScorePO zhimaScorePO = zhimaScorePOFuture.get();
        verify(fetchService, times(1)).fetchZhimaScore("700", "700");
        assertEquals(Color.SUCCESS, zhimaScorePO.getColor());
    }

    @Test
    public void testMockNullAsyncHandleZhimaScore() throws ExecutionException, InterruptedException {
        when(fetchService.fetchZhimaScore(null, null)).thenReturn(null);

        PersonReport report = new PersonReport();
        Future<ZhimaScorePO> zhimaScorePOFuture = analyseService.asyncHandleZhimaScore(report);
        ZhimaScorePO zhimaScorePO = zhimaScorePOFuture.get();
        verify(fetchService, times(1)).fetchZhimaScore(null, null);
        assertEquals(Color.TIMEOUT, zhimaScorePO.getColor());
    }

    @Test
    public void testMockErrorAsyncHandleZhimaScore() throws ExecutionException, InterruptedException, IOException {
        final String mockZhimaScoreNullStr = "{\"score\":null}";
        Map mockZhimaScireNull = new ObjectMapper().readValue(mockZhimaScoreNullStr, Map.class);
        when(fetchService.fetchZhimaScore("", "")).thenReturn(mockZhimaScireNull);

        PersonReport report = new PersonReport();
        report.setName("");
        report.setIdCard("");
        Future<ZhimaScorePO> zhimaScorePOFuture = analyseService.asyncHandleZhimaScore(report);
        ZhimaScorePO zhimaScorePO = zhimaScorePOFuture.get();
        verify(fetchService, times(1)).fetchZhimaScore("", "");
        assertEquals(Color.ERROR, zhimaScorePO.getColor());
    }

    //////////////////////////////zhimaScire end
    @Test
    public void testMockHandleAuthMobile() throws IOException {
        final String mockStr = "{\"orderNo\":\"201709061056396790389\",\"data\":{\"result\":\"T\",\"message\":\"信息验证通过\"},\"rc\":\"0000\",\"msg\":\"查询成功\"}";
        Map mock = new ObjectMapper().readValue(mockStr, Map.class);
        when(fetchService.fetchAuthMobile(anyString(), anyString(), anyString())).thenReturn(mock);

        Boolean result = analyseService.handleAuthMobile("", "", "");
        assertTrue(result);
    }

    @Test
    public void testMockFailHandleAuthMobile() throws IOException {
        final String mockStr = "{\"orderNo\":\"201709061056396790389\",\"data\":{\"result\":\"F\",\"message\":\"信息验证不通过\"},\"rc\":\"0000\",\"msg\":\"查询成功\"}";
        Map mock = new ObjectMapper().readValue(mockStr, Map.class);
        when(fetchService.fetchAuthMobile(anyString(), anyString(), anyString())).thenReturn(mock);

        Boolean result = analyseService.handleAuthMobile("", "", "");
        assertFalse(result);
    }

    @Test
    public void testMockNullHandleAuthMobile() throws IOException {
        final String mockStr = "{\"rc\":\"1000\",\"msg\":\"apikey校验失败\"}";
        Map mock = new ObjectMapper().readValue(mockStr, Map.class);
        when(fetchService.fetchAuthMobile(anyString(), anyString(), anyString())).thenReturn(mock);

        Boolean result = analyseService.handleAuthMobile("", "", "");
        assertNull(result);
    }


}
