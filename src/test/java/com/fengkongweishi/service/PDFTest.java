package com.fengkongweishi.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import fengkongweishi.controller.PrintFileController;
import freemarker.template.Configuration;
import fengkongweishi.util.Common;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;
import org.springframework.util.StringUtils;


import java.io.*;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * @author jianger
 * @Description
 * @Date 2018/1/31 上午9:38
 **/
public class PDFTest {

    /**
     * 测试PDF模板下载
     */
    @Test
    public void testPDFWithTemplate() throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("name", "haha");
        map.put("age", 12);
        Map<String, Object> subMap = new HashMap<>();
        subMap.put("id", 1);
        subMap.put("acts", "money");
        map.put("like", subMap);

        createPDF(map, "helloworld.pdf");


    }

    @Test
    public void testRandomUID() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(6);
        int i = 11;
        String result = format.format(i).replace(",", "");
        System.out.println(result);

    }

    @Test
    public void testClassPath() {
//        System.out.println(Common.class.getClassLoader().getResource("").getPath());
        System.out.println(this.getClass().getResource("/static/pdf.html").getPath());
        System.out.println(PrintFileController.class.getResource("/").getPath());
        System.out.println(System.getProperty("user.dir"));
    }

    public static String createPDF(Object data, String fileName) throws Exception {
        PDFKit pdfKit = new PDFKit();
        pdfKit.setSaveFilePath("/Users/ginger/Desktop/helloworld.pdf");
        String saveFilePath = pdfKit.exportToFile(fileName, data);
        return saveFilePath;
    }


    public static class PDFKit {
        private String saveFilePath;

        public String getSaveFilePath() {
            return saveFilePath;
        }

        public void setSaveFilePath(String saveFilePath) {
            this.saveFilePath = saveFilePath;
        }

        /**
         * 导出PDF
         *
         * @param fileName
         * @param data
         * @return
         */
        public String exportToFile(String fileName, Object data) throws Exception {
            String htmlData = getContent(fileName, data);
            System.out.println(htmlData);
            if (StringUtils.isEmpty(saveFilePath)) {
                saveFilePath = getDefaultSavePath(fileName);
            }
            File file = new File(saveFilePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream outputStream = null;
            outputStream = new FileOutputStream(saveFilePath);
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            convertToPDF(writer, document, htmlData);
            return null;
        }

        private void convertToPDF(PdfWriter writer, Document document, String htmlData) throws IOException {
            String fontPath = "/Users/ginger/svn/xiaojinshuju/target/test-classes/fonts";
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(htmlData.getBytes()),
                    XMLWorkerHelper.class.getResourceAsStream("/default.css"),
                    Charset.forName("UTF-8"), new XMLWorkerFontProvider(fontPath));
            document.close();
        }

        private String getDefaultSavePath(String fileName) {
            String classpath = "/Users/ginger/svn/xiaojinshuju/target/test-classes/";
            String saveFilePath = classpath + "pdf/" + fileName;
            File f = new File(saveFilePath);
            if (!f.getParentFile().exists()) {
                f.mkdirs();
            }
            return saveFilePath;
        }
    }

    public static String getContent(String fileName, Object data) throws Exception {
//        String templatePath = getPDFTemplatePath(fileName);
        String templatePath = "/Users/ginger/svn/xiaojinshuju/target/test-classes/templates/hello.ftl";
        String templateFileName = StringUtils.isEmpty(templatePath) ? "" : templatePath.substring(0, templatePath.lastIndexOf("/") + 1);
        String templateFilePath = StringUtils.isEmpty(templatePath) ? "" : templatePath.substring(0, templatePath.lastIndexOf("/"));
        if (StringUtils.isEmpty(templatePath)) {
            throw new Exception("templatePath can not be empty!");
        }
        System.out.println(templateFileName);
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setDirectoryForTemplateLoading(new File(templateFilePath));
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            Template template = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            String html = writer.toString();
            return html;
        } catch (Exception e) {
            throw new Exception("模板生成失败", e);
        }
    }

    private static String getPDFTemplatePath(String fileName) throws Exception {
        String classpath = Common.class.getClassLoader().getResource("").getPath();
        String templatePath = classpath + "/templates";
        File file = new File(templatePath);
        if (!file.isDirectory()) {
            throw new Exception("PDF模板文件不存在,请检查templates文件夹!");
        }
        String pdfFileName = fileName.substring(0, fileName.lastIndexOf("."));
        File defaultTemplate = null;
        File matchTemplate = null;
        for (File f : file.listFiles()) {
            if (!f.isFile()) {
                continue;
            }
            String templateName = f.getName();
            if (templateName.lastIndexOf(".ftl") == -1) {
                continue;
            }
            if (defaultTemplate == null) {
                defaultTemplate = f;
            }
            if (StringUtils.isEmpty(fileName) && defaultTemplate != null) {
                break;
            }
        }

        return null;
    }


}
