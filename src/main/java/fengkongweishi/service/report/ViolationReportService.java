package fengkongweishi.service.report;

import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.personreport.po.ViolationPO;
import fengkongweishi.enums.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 违章信息查询,插入报告
 *
 * @author huanghengkun
 * @date 2018/02/01
 */
@Service
public class ViolationReportService implements IReportService {

    @Autowired
    AnalyseService analyseService;
    @Autowired
    PersonReportRepository personReportRepository;

    @Override
    public void analyseReport(PersonReport report) {
        Future<ViolationPO> violationPOFuture = analyseService.asyncHandleViolation(report);
        try {
            ViolationPO violationPO = violationPOFuture.get(21, TimeUnit.SECONDS);
            report.setViolationPO(violationPO);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            report.setViolationPO(new ViolationPO(Color.TIMEOUT));
        }
        report.setFinishAt(new Date());
        personReportRepository.save(report);
    }
}
