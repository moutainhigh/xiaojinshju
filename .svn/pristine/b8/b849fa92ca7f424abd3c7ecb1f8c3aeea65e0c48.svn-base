package fengkongweishi.controller;

import fengkongweishi.service.FileUploadService;
import fengkongweishi.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 *
 * @author huanghengkun
 * @date 2018/01/08
 */
@RestController()
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
    public ResponseBody headUpload(@RequestParam(value = "file") MultipartFile head) {
        ResponseBody result = new ResponseBody();
        FileUploadService.OssFile ossFile = fileUploadService.uploadByFileTypeName(head, FileUploadService.FILE_TYPE_IMAGE);
        return new ResponseBody(ossFile);
    }

    /**
     * @Description 文件上传
     * @author jianger
     * @Date 2018/1/31 上午11:32
     **/
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public ResponseBody fileUpload(@RequestParam(value = "file") MultipartFile head) {
        FileUploadService.OssFile ossFile = fileUploadService.uploadByFileTypeName(head, FileUploadService.FILE_TYPE_FILE);
        return new ResponseBody(ossFile);
    }

}
