package fengkongweishi.thread;

import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.service.report.MoxieCarrierReportService;
import fengkongweishi.websocket.ReportSocket;
import fengkongweishi.websocket.reportSocketMessage;

import javax.transaction.Transactional;

/**
 * @author huanghengkun
 * @date 2018/01/22
 */
public class MoxieCarrierReportThread implements Runnable {

    PersonReport report;
    MoxieCarrierReportService moxieCarrierReportService;

    public MoxieCarrierReportThread(PersonReport report, MoxieCarrierReportService moxieCarrierReportService) {
        this.report = report;
        this.moxieCarrierReportService = moxieCarrierReportService;
    }

    @Override
    @Transactional(rollbackOn = ExceptionEnum.class)
    public void run() {
        try {
            moxieCarrierReportService.analyseReport(report);
            ReportSocket.sendMessage(new reportSocketMessage(report.getId(), true));
        } catch (Exception e) {
            e.printStackTrace();
            ReportSocket.sendMessage(new reportSocketMessage(report.getId(), false));
        }

    }
}
