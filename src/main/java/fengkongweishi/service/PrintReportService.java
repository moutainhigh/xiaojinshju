package fengkongweishi.service;

import fengkongweishi.entity.customersearchlog.CustomerSearchLog;
import fengkongweishi.entity.customersearchlog.CustomerSearchLogRepository;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.personreport.PersonReportVO;
import fengkongweishi.enums.SystemEditionEnum;
import fengkongweishi.service.report.PdfHelper;
import fengkongweishi.util.BeanToMapUtils;
import fengkongweishi.util.PDFKit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jianger
 * @Description 打印报告相关
 * @Date 2018/1/31 下午5:49
 **/
@Service
public class PrintReportService {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private PersonReportRepository personReportRepository;
    @Autowired
    private CustomerSearchLogRepository customerSearchLogRepository;
    private static int reportNo = 10;

    /**
     * 打印PDF报告
     * 1.获取报告数据
     * 2.根据不同的模板调用方法生成PDF，返回阿里云结果
     *
     * @return
     */
    public String printReportPDF(Integer id) throws Exception {
        PersonReport personReport = personReportRepository.findOne(id);
        if (StringUtils.isNotBlank(personReport.getPdfUrl())) {
            return personReport.getPdfUrl();
        }
        Map<String, Object> map = getReportDetails(personReport);

        String pdfUrl = createPDF(map, "hello.ftl");

        personReport.setPdfUrl(pdfUrl);
        personReportRepository.save(personReport);
        return pdfUrl;
    }


    public OutputStream generateToFile(String ftlPath, String ftlName, String imageDiskPath, Object data) throws Exception {
        String html = PdfHelper.getPdfContent(ftlPath, ftlName, data);
        PdfHelper pdfHelper = new PdfHelper();
        OutputStream out = null;
        ITextRenderer render = null;
        out = new ByteArrayOutputStream();
        render = pdfHelper.getRender();
        render.setDocumentFromString(html);
        if (imageDiskPath != null && !imageDiskPath.equals("")) {
            //html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径
            render.getSharedContext().setBaseURL("file:/" + imageDiskPath);
        }
        render.layout();
        render.createPDF(out);
        render.finishPDF();
        render = null;

        out.close();

        return out;
    }

    /**
     * 获取模板名称
     *
     * @param
     * @return
     */
    private String getTemplateFileName(PersonReport personReport) throws Exception {
        SystemEditionEnum systemEditionEnum = personReport.getEdition();
        if (systemEditionEnum.equals(SystemEditionEnum.PERSONJUNIOR)) {
            return "basic";//基础版
        }

        if (systemEditionEnum.equals(SystemEditionEnum.PERSONECOMMERCE)) {
            return "taobao";//淘宝版
        }
        if (systemEditionEnum.equals(SystemEditionEnum.PERSONMOBILE)) {
            return "communication";//通讯版
        }
        if (systemEditionEnum.equals(SystemEditionEnum.PERSONSENIOR)) {
            return "whole";//全面版
        } else {
            throw new Exception("未找到匹配的模板");
        }

    }

    /**
     * 1.调用模板生成PDF
     * 2.上传阿里云，返回URL链接
     *
     * @param data     报告数据
     * @param fileName 模板名称
     * @return
     * @throws Exception
     */
    public String createPDF(Object data, String fileName) throws Exception {
        PDFKit pdfKit = new PDFKit();
        String ftlPath = pdfKit.getTemplateFilePath(pdfKit.getTemplatePath(fileName));
        String imageDiskPath = "resources";

        OutputStream outputStream = generateToFile(ftlPath, fileName, imageDiskPath, data);
        Map<String, Object> map = new HashMap<>();
        map.put("newFileName", formatDateTime() + ".pdf");
        return fileUploadService.upload(map, parseOutputStreamToInputStream(outputStream));

    }


    /**
     * 获取报告详情相应的数据
     */
    public Map<String, Object> getReportDetails(PersonReport personReport) {
        CustomerSearchLog customerSearchLog = customerSearchLogRepository.findByReport(personReport);
        PersonReportVO reportVo = new PersonReportVO(personReport, customerSearchLog.getLevel());
        Map<String, Object> map = BeanToMapUtils.beanToMap(reportVo);

//        for (Map.Entry<String, Object> m : map.entrySet()) {
//            if ("callListVO".equals(m.getKey())) {
////                CallListVO callListVO = new CallListVO();
////                callListVO = (CallListVO) m.getValue();
////                Map maps = new HashMap();
////                map.remove("callListVO");
////                maps.put("callList", callListVO.getCallList());
////                maps.put("id", callListVO.getId());
////                maps.put("color", callListVO.getColor());
////                map.put("callListVO", maps);
////                System.out.println(m.getValue());
////                map.remove()
//            }
//
//            if ("courtJudgmentVO".equals(m.getKey())) {
//                System.out.println(m.getValue());
//            }
//
//        }
        /*map.remove("callListVO");
        CallListVO callListVO = personReport.getCallListVO();
        Map<String, Object> mapss = BeanToMapUtils.beanToMap(callListVO);
        map.put("callListVO", mapss);*/

        map.put("reportId", getReportId());
        map.put("createByName", personReport.getCreateBy().getNickname());
        String createByCompany = null;
        if (personReport.getCreateByCompany().getCompanyName() != null) {
            createByCompany = personReport.getCreateByCompany().getCompanyName();
        }
        map.put("createByCompany", createByCompany);
        return map;
    }


    /**
     * 将输出流装换为输入流
     *
     * @return
     */
    public InputStream parseOutputStreamToInputStream(OutputStream outputStream) {
        ByteArrayOutputStream byteArrayOutputStream
                = (ByteArrayOutputStream) outputStream;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }


    /**
     * 获取当前时间为PDF文件名
     *
     * @return
     */
    public String formatDateTime() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String yearStr = calendar.get(Calendar.YEAR) + "";//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        String monthStr = month < 10 ? "0" + month : month + "";
        int day = calendar.get(Calendar.DATE);//获取日
        String dayStr = day < 10 ? "0" + day : day + "";

        String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
        String minute = calendar.get(Calendar.MINUTE) + "";
        String second = calendar.get(Calendar.SECOND) + "";

        return yearStr + monthStr + dayStr + hour + minute + second;
    }

    /**
     * 生成报告编号
     *
     * @return
     */
    public String getReportId() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(8);

        String result = format.format(reportNo).replace(",", "");
        reportNo++;
        return formatDateTime() + result;
    }


}
