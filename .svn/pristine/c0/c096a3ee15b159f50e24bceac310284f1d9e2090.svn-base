package com.fengkongweishi.service;

import fengkongweishi.Application;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.po.*;
import fengkongweishi.enums.Color;
import fengkongweishi.enums.SystemEditionEnum;
import fengkongweishi.service.report.AnalyseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author huanghengkun
 * @date 2018/01/110
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
//@TestExecutionListeners(listeners = {SpringBootDependencyInjectionTestExecutionListener.class, ServletTestExecutionListener.class})
public class AnalyseServiceTest {

    @Autowired
    private AnalyseService analyseService;

    static final String NAME = "王樑";
    static final String ID_CARD = "330726198703014914";
    static final String MOBILE = "15088278899";
    static final String BANK_CARD = "62306110710144610616";
    static final String ADDRESS = "浙江省杭州市滨江区创伟科技园A栋16楼";
    static final String PLATENUMBER = "浙AX698A";
    static final String ENGINENO = "F618H301B38A15A";
    static final String VIN = "WMWLU9106H2C27942";
    static final String CARTYPE = "02";

    static PersonReport REPORT = new PersonReport(NAME, MOBILE, ID_CARD, BANK_CARD, ADDRESS, SystemEditionEnum.PERSONJUNIOR);

    @Test
    public void testAsyncHandleIdCard() {
        Future<Map> mapFuture = analyseService.asyncHandleIdCard(REPORT);
        IdCardPO idCardPO = null;
        PhonePO phonePO = null;
        BankPO bankPO = null;
        try {
            Map map = mapFuture.get(10, TimeUnit.SECONDS);
            idCardPO = (IdCardPO) map.get("idCardPO");
            phonePO = (PhonePO) map.get("phonePO");
            bankPO = (BankPO) map.get("bankPO");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println(idCardPO);
        System.out.println(phonePO);
        System.out.println(bankPO);
        System.out.println("--------------------------------------");
        Assert.assertNotNull(idCardPO);
        Assert.assertNotNull(phonePO);
        Assert.assertNotNull(bankPO);
        Assert.assertNotEquals(Color.TIMEOUT, idCardPO.getColor());
        Assert.assertNotEquals(Color.TIMEOUT, phonePO.getColor());
        Assert.assertNotEquals(Color.TIMEOUT, bankPO.getColor());
    }
    
    @Test
    public void testAsyncHandleViolation() {
    	REPORT.setPlateNumber(PLATENUMBER);
    	REPORT.setCarType(CARTYPE);
    	REPORT.setVin(VIN);
    	REPORT.setEngineNo(ENGINENO);
        Future<ViolationPO> violationFuture = analyseService.asyncHandleViolation(REPORT);
        ViolationPO violationPO = null;
        try {
            violationPO = violationFuture.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println(violationPO);
        System.out.println("--------------------------------------");
        Assert.assertNotNull(violationPO);
        Assert.assertNotEquals(Color.TIMEOUT, violationPO.getColor());
    }

    @Test
    public void testAsyncHandleEducation() {
        Future<EducationPO> educationPOFuture = analyseService.asyncHandleEducation(REPORT);
        EducationPO educationPO = null;
        try {
            educationPO = educationPOFuture.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println(educationPO);
        System.out.println("--------------------------------------");
        Assert.assertNotNull(educationPO);
        Assert.assertNotEquals(Color.TIMEOUT, educationPO.getColor());
    }

    @Test
    public void testAsyncHandleZhimaScore() {
        Future<ZhimaScorePO> zhimaScorePOFuture = analyseService.asyncHandleZhimaScore(REPORT);
        ZhimaScorePO zhimaScorePO = null;
        try {
            zhimaScorePO = zhimaScorePOFuture.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println(zhimaScorePO);
        System.out.println("--------------------------------------");
        Assert.assertNotNull(zhimaScorePO);
        Assert.assertNotEquals(Color.TIMEOUT, zhimaScorePO.getColor());
    }


    @Test
    public void testAsyncHandleCriminal() {
        Future<CriminalPO> criminalPOFuture = analyseService.asyncHandleCriminal(REPORT);
        CriminalPO criminalPO = null;
        try {
            criminalPO = criminalPOFuture.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println(criminalPO);
        System.out.println("--------------------------------------");
        Assert.assertNotNull(criminalPO);
        Assert.assertNotEquals(Color.TIMEOUT, criminalPO.getColor());
    }

    @Test
    public void testAsyncHandleDishonestBlack() {
        Future<DishonestBlackPO> dishonestBlackPOFuture = analyseService.asyncHandleDishonestBlack(REPORT);
        DishonestBlackPO dishonestBlackPO = null;
        try {
            dishonestBlackPO = dishonestBlackPOFuture.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println(dishonestBlackPO);
        System.out.println("--------------------------------------");
        Assert.assertNotNull(dishonestBlackPO);
        Assert.assertNotEquals(Color.TIMEOUT, dishonestBlackPO.getColor());
    }

    @Test
    public void testAsyncHandleCourtJudgment() {
        Future<CourtJudgmentPO> courtJudgmentPOFuture = analyseService.asyncHandleCourtJudgment(REPORT);
        CourtJudgmentPO courtJudgmentPO = null;
        try {
            courtJudgmentPO = courtJudgmentPOFuture.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println(courtJudgmentPO);
        System.out.println("--------------------------------------");
        Assert.assertNotNull(courtJudgmentPO);
        Assert.assertNotEquals(Color.TIMEOUT, courtJudgmentPO.getColor());
    }

}
