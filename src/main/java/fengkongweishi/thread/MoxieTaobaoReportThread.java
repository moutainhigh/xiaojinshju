package fengkongweishi.thread;

import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.service.report.MoxieCarrierReportService;
import fengkongweishi.service.report.MoxieTaobaoReportService;
import fengkongweishi.websocket.ReportSocket;
import fengkongweishi.websocket.reportSocketMessage;

import javax.transaction.Transactional;

/**
 * @author huanghengkun
 * @date 2018/01/22
 */
public class MoxieTaobaoReportThread implements Runnable {

    PersonReport report;
    MoxieTaobaoReportService moxieTaobaoReportService;

    public MoxieTaobaoReportThread(PersonReport report, MoxieTaobaoReportService moxieTaobaoReportService) {
        this.report = report;
        this.moxieTaobaoReportService = moxieTaobaoReportService;
    }

    @Override
    @Transactional(rollbackOn = ExceptionEnum.class)
    public void run() {
        try {
        	moxieTaobaoReportService.analyseReport(report);
            ReportSocket.sendMessage(new reportSocketMessage(report.getId(), true));
        } catch (Exception e) {
            e.printStackTrace();
            ReportSocket.sendMessage(new reportSocketMessage(report.getId(), false));
        }

    }
}
