package fengkongweishi.service.report;

import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.personreport.po.*;
import fengkongweishi.enums.Color;
import fengkongweishi.enums.SearchStatusEnum;
import fengkongweishi.enums.SystemEditionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author huanghengkun
 * @date 2018/01/17
 */
@Service
public class BaseReportService implements IReportService {

    @Autowired
    AnalyseService analyseService;
    @Autowired
    PersonReportRepository personReportRepository;

    @Override
    public void analyseReport(PersonReport report) {
        List<IAnalyseItem> results = new ArrayList<>();

        Future<Map> idCardFuture = analyseService.asyncHandleIdCard(report);
        Future<IdCheckPO> idCheckPOFuture = analyseService.asyncHandleIdCheck(report);
        Future<ZhimaScorePO> zhimaScorePOFuture = analyseService.asyncHandleZhimaScore(report);
        Future<CriminalPO> criminalPOFuture = analyseService.asyncHandleCriminal(report);
        Future<DishonestBlackPO> dishonestBlackPOFuture = analyseService.asyncHandleDishonestBlack(report);
        Future<CourtJudgmentPO> courtJudgmentPOFuture = analyseService.asyncHandleCourtJudgment(report);
        Future<BlackRiskPO> blackRiskPOFuture = analyseService.asyncHandleBlackRisk(report);
        if (SystemEditionEnum.PERSONSENIOR.equals(report.getEdition())) {
            Future<EducationPO> educationPOFuture = analyseService.asyncHandleEducation(report);
            try {
                EducationPO educationPO = educationPOFuture.get(10, TimeUnit.SECONDS);
                results.add(educationPO);
                report.setEducationPO(educationPO);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
                EducationPO educationPO = new EducationPO(Color.TIMEOUT);
                results.add(educationPO);
                report.setEducationPO(educationPO);
            }
        }
        try {
            Map map = idCardFuture.get(10, TimeUnit.SECONDS);
            IdCardPO idCardPO = (IdCardPO) map.get("idCardPO");
            PhonePO phonePO = (PhonePO) map.get("phonePO");
            BankPO bankPO = (BankPO) map.get("bankPO");
            results.add(idCardPO);
            results.add(phonePO);
            results.add(bankPO);
            report.setIdCardPO(idCardPO);
            report.setPhonePO(phonePO);
            report.setBankPO(bankPO);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            IdCardPO idCardPO = new IdCardPO(Color.TIMEOUT);
            PhonePO phonePO = new PhonePO(Color.TIMEOUT);
            BankPO bankPO = new BankPO(Color.TIMEOUT);
            results.add(idCardPO);
            results.add(phonePO);
            results.add(bankPO);
            report.setIdCardPO(idCardPO);
            report.setPhonePO(phonePO);
            report.setBankPO(bankPO);
        }
        try {
            IdCheckPO idCheckPO = idCheckPOFuture.get(10, TimeUnit.SECONDS);
            results.add(idCheckPO);
            report.setIdCheckPO(idCheckPO);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            IdCheckPO idCheckPO = new IdCheckPO(Color.TIMEOUT);
            results.add(idCheckPO);
            report.setIdCheckPO(idCheckPO);
        }
        try {
            ZhimaScorePO zhimaScorePO = zhimaScorePOFuture.get(10, TimeUnit.SECONDS);
            results.add(zhimaScorePO);
            report.setZhimaScorePO(zhimaScorePO);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            ZhimaScorePO zhimaScorePO = new ZhimaScorePO(Color.TIMEOUT);
            results.add(zhimaScorePO);
            report.setZhimaScorePO(zhimaScorePO);
        }
        try {
            CriminalPO criminalPO = criminalPOFuture.get(10, TimeUnit.SECONDS);
            results.add(criminalPO);
            report.setCriminalPO(criminalPO);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            CriminalPO criminalPO = new CriminalPO(Color.TIMEOUT);
            results.add(criminalPO);
            report.setCriminalPO(criminalPO);
        }
        try {
            DishonestBlackPO dishonestBlackPO = dishonestBlackPOFuture.get(10, TimeUnit.SECONDS);
            results.add(dishonestBlackPO);
            report.setDishonestBlackPO(dishonestBlackPO);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            DishonestBlackPO dishonestBlackPO = new DishonestBlackPO(Color.TIMEOUT);
            results.add(dishonestBlackPO);
            report.setDishonestBlackPO(dishonestBlackPO);
        }
        try {
            CourtJudgmentPO courtJudgmentPO = courtJudgmentPOFuture.get(10, TimeUnit.SECONDS);
            results.add(courtJudgmentPO);
            report.setCourtJudgmentPO(courtJudgmentPO);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            CourtJudgmentPO courtJudgmentPO = new CourtJudgmentPO(Color.TIMEOUT);
            results.add(courtJudgmentPO);
            report.setCourtJudgmentPO(courtJudgmentPO);
        }
        try {
            BlackRiskPO blackRiskPO = blackRiskPOFuture.get(10, TimeUnit.SECONDS);
            results.add(blackRiskPO);
            report.setBlackRiskPO(blackRiskPO);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            BlackRiskPO blackRiskPO = new BlackRiskPO(Color.TIMEOUT);
            results.add(blackRiskPO);
            report.setBlackRiskPO(blackRiskPO);
        }
        long errorCount = results.stream().filter(item -> Color.TIMEOUT.equals(item.getColor())).count();
        if (errorCount > 0) {
            report.setStatus(SearchStatusEnum.ERROR);
        } else {
            report.setStatus(SearchStatusEnum.SUCCESS);
        }
        report.setFinishAt(new Date());
        personReportRepository.save(report);
    }
}
