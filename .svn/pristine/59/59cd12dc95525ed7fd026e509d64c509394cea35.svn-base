package fengkongweishi.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author jianger
 * @Description pdf模板相关
 * @Date 2018/2/3 下午2:57
 **/
public class PDFKit {

    /**
     * 导出PDF
     *
     * @param fileName
     * @param data
     * @return
     */
    public OutputStream exportToFile(String fileName, Object data) throws Exception {
        String htmlData = getContent(fileName, data);
//        System.out.println(htmlData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A3);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        convertToPDF(writer, document, htmlData);

        outputStream.close();
        return outputStream;
    }

    /**
     * 1.获取模板
     * 2.填充数据
     *
     * @param fileName
     * @param data
     * @return 完整的HTML页面
     * @throws Exception
     */
    public String getContent(String fileName, Object data) throws Exception {
        String templatePath = getTemplatePath(fileName);
        String templateFileName = getTemplateName(templatePath);
        String templateFilePath = getTemplateFilePath(templatePath);
        if (StringUtils.isEmpty(templatePath)) {
            throw new Exception("templatePath can not be empty!");
        }
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setDirectoryForTemplateLoading(new File(templateFilePath));
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            Template template = configuration.getTemplate(templateFileName);
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            String html = writer.toString();
            return html;
        } catch (Exception e) {
            throw new Exception("模板生成失败", e);
        }
    }

    /**
     * 获取模板路径
     *
     * @return
     */
    public String getTemplatePath(String templateFileName) throws Exception {

        String classpath = this.getClass().getResource("/").getPath();
        String templatePath = classpath + "/templates";
        File file = new File(templatePath);
        if (!file.isDirectory()) {
            throw new Exception("PDF模板文件不存在,请检查templates文件夹!");
        }
        String pdfFileName = templateFileName.substring(0, templateFileName.lastIndexOf("."));
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
            if (org.apache.commons.lang.StringUtils.isEmpty(templateFileName) && defaultTemplate != null) {
                break;
            }
            templateName = templateName.substring(0, templateName.lastIndexOf("."));
            if (templateName.toLowerCase().equals(pdfFileName.toLowerCase())) {
                matchTemplate = f;
                break;
            }
        }
        if (matchTemplate != null) {
            return matchTemplate.getAbsolutePath();
        }
        if (defaultTemplate != null) {
            return defaultTemplate.getAbsolutePath();
        }

        return null;

    }

    /**
     * HTML转PDF
     *
     * @param writer
     * @param document
     * @param htmlData
     * @throws IOException
     */
    private void convertToPDF(PdfWriter writer, Document document, String htmlData) throws IOException {
        String fontPath = this.getClass().getResource("/").getPath() + "static/fonts";
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(htmlData.getBytes()),
                XMLWorkerHelper.class.getResourceAsStream("/default.css"),
                Charset.forName("UTF-8"), new XMLWorkerFontProvider(fontPath));
        document.close();
    }

    /**
     * 获取模板名字
     *
     * @param templatePath
     * @return
     */
    public String getTemplateName(String templatePath) {
        if (org.apache.commons.lang.StringUtils.isEmpty(templatePath)) {
            return "";
        }
        String s;
        if (templatePath.contains("\\")) {
            s = "\\";
        } else {
            s = "/";
        }
        String fileName = templatePath.substring(templatePath.lastIndexOf(s) + 1);
        return fileName;
    }

    /**
     * 获取模板路径
     *
     * @param templatePath
     * @return
     */
    public String getTemplateFilePath(String templatePath) {
        if (org.apache.commons.lang.StringUtils.isEmpty(templatePath)) {
            return "";
        }
        String s;
        if (templatePath.contains("\\")) {
            s = "\\";
        } else {
            s = "/";
        }
        String path = templatePath.substring(0, templatePath.lastIndexOf(s));
        return path;
    }

}

