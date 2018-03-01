package fengkongweishi.service.report;

import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.entity.personreport.po.TaoBaoPO;
import fengkongweishi.entity.personreport.vo.*;
import fengkongweishi.enums.Color;
import fengkongweishi.enums.MoxieTaskStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author huanghengkun
 * @date 2018/01/22
 */
@Service
public class MoxieTaobaoReportService implements IReportService {

	@Autowired
	AnalyseService analyseService;
	@Autowired
	PersonReportRepository personReportRepository;

	@Override
	public void analyseReport(PersonReport report) {
		if (MoxieTaskStatusEnum.REPORT_SUCCESS.equals(report.getMoxieTaskTaobaoStatus())) {
			TaoBaoPO taoBaoPO = analyseService.handleMoxieTaoBao(report);
			if (taoBaoPO != null) {
				report.setTaoBaoPO(taoBaoPO);
			} else {
				report.setTaoBaoPO(new TaoBaoPO(Color.ERROR));
			}
		} else {
		    report.setTaoBaoPO(new TaoBaoPO(Color.ERROR));
		}
		report.setFinishAt(new Date());
		personReportRepository.save(report);
	}
}
