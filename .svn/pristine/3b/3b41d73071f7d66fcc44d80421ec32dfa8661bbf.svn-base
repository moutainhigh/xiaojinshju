package com.fengkongweishi.service;

import fengkongweishi.Application;
import fengkongweishi.service.PrintReportService;
import fengkongweishi.service.report.FetchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.ServletTestExecutionListener;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author huanghengkun
 * @date 2018/01/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
@TestExecutionListeners(listeners = {SpringBootDependencyInjectionTestExecutionListener.class, ServletTestExecutionListener.class})
public class FetchServiceTest {

    @Autowired
    private FetchService fetchService;
    @Autowired
    private PrintReportService printReportService;

    @Test
    public void testFetchAntiFraud() {
        String idCard = "331021199212261270";
        String mobile = "18268850390";
        String name = "黄恒坤";
        Map result = fetchService.fetchCsecAntiFraud(idCard, mobile, name);
        System.out.println(result);
    }

    @Test
    public void testFetchBankCard() {
        String bankcard = "6228480018190607277";
        Map result = fetchService.fetchBankCard(bankcard);
        System.out.println(result);
    }

    @Test
    public void testFetchViolationXK() {
        String plateNumber = "浙AX698A";
        String vin = "WMWLU9106H2C27942";
        String engineNo = "F618H301B38A15A";
        String carType = "02";
        Map result = fetchService.fetchViolationXK(plateNumber, vin, engineNo, carType);
        System.out.println(result);
    }

    @Test
    public void testFetchViolationWS() {
        String plateNumber = "浙AX698A";
        String vin = "WMWLU9106H2C27942";
        String engineNo = "F618H301B38A15A";
        String carType = "02";
        String mobile = "";
        Map result = fetchService.fetchViolationWS(plateNumber, vin, engineNo, carType, mobile);
        System.out.println(result);
    }

    @Test
    public void testFetchOcrVehicle() {
        String fileName = "/Users/ginger/Desktop/WechatIMG3.jpeg";
        String base64Code = getImageStr(fileName);
        Map result = fetchService.fetchOcrVehicle(base64Code);
        System.out.println(result);
    }

    @Test
    public void testFetchXinShuRiskA() {
        String name = "郑飘飘";
        String idCard = "330726198111132765";
        String mobile = "13750948090";
        String bankCard = "6217001460004117064";
        Map result = fetchService.fetchXinShuRiskA(name, idCard, mobile, bankCard);
        System.out.println(result);
    }

    @Test
    public void testFetchXinShuAuthMobile() {
        String name = "郑飘飘";
        String idCard = "330726198111132765";
        String mobile = "13750948090";
        Map result = fetchService.fetchAuthMobile(name, idCard, mobile);
        System.out.println(result);
    }

    @Test
    public void testFetchXinShuCourtSimple() {
        String q = "郑飘飘";
        Map result = fetchService.fetchXinShuCourtSimple(q, "");
        System.out.println(result);
    }

    @Test
    public void testFetchXinShuCourtDetail() {
        String id = "98564632F5282FC91862F235EB3002C7";
        String datatype = "cpws";
        Map result = fetchService.fetchXinShuCourtDetail(id, datatype);
        System.out.println(result);
    }

    public static String getImageStr(String imgFile) {
        InputStream inputStream;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    @Test
    public void testPDF() throws Exception {

//        System.out.println(printReportService.printReportPDF(1));
        System.out.println(printReportService.getReportId());
        System.out.println(printReportService.getReportId());
    }
}
