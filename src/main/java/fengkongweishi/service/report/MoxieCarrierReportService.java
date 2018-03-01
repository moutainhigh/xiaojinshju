package fengkongweishi.service.report;

import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.personreport.po.CarrierPO;
import fengkongweishi.enums.Color;
import fengkongweishi.enums.MoxieTaskStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author huanghengkun
 * @date 2018/01/22
 */
@Service
public class MoxieCarrierReportService implements IReportService {

    @Autowired
    AnalyseService analyseService;
    @Autowired
    PersonReportRepository personReportRepository;

    @Override
    public void analyseReport(PersonReport report) {
        if (MoxieTaskStatusEnum.REPORT_SUCCESS.equals(report.getMoxieTaskCarrierStatus())) {
            CarrierPO carrierPO = analyseService.handleMoxieCarrier(report);
            if (carrierPO != null) {
                report.setCarrierPO(carrierPO);
            } else {
                report.setCarrierPO(new CarrierPO(Color.ERROR));
            }
        } else {
            report.setCarrierPO(new CarrierPO(Color.ERROR));
        }
        report.setFinishAt(new Date());
        personReportRepository.save(report);
    }
}
