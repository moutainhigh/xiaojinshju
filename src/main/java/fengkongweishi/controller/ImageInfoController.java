package fengkongweishi.controller;

import fengkongweishi.entity.imageinfo.VehicleLicenseVO;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.service.ImageInfoService;
import fengkongweishi.util.FailResponse;
import fengkongweishi.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author jianger
 * @Description 识别图片
 * @date 2018/1/29 下午3:52
 **/
@RestController
@RequestMapping("/image")
public class ImageInfoController {
    @Autowired
    private ImageInfoService imageInfoService;

    /**
     * 识别上传行驶证
     *
     * @param file 行驶证
     */
    @RequestMapping(value = "/distinguish", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER') or hasRole('EMPLOYEE')")
    public ResponseBody distinguish(@RequestParam(value = "file") MultipartFile file) {
        try {
            VehicleLicenseVO vehicleLicenseVO = imageInfoService.handleBaiduVehicleLicense(file.getBytes());
            return new ResponseBody(vehicleLicenseVO);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailResponse(ExceptionEnum.IMAGE_IO_ERROR);
        }
    }


    private String getBase64Code(MultipartFile file) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(file.getBytes());
    }

}
