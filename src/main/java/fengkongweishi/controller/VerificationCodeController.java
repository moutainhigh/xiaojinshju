package fengkongweishi.controller;

import fengkongweishi.util.ImageUtil;
import fengkongweishi.util.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huanghengkun
 * @date 2018/01/24
 */
@RestController
@RequestMapping("/verification")
public class VerificationCodeController {

    private static final BASE64Encoder ENCODER = new sun.misc.BASE64Encoder();

    /**
     * 图片验证码,base64加密
     */
    @RequestMapping(value = "/codeandimage",method = RequestMethod.GET)
    public ResponseBody getVerificationCode() {
        Object[] codeAndImage = ImageUtil.createImage();
        String code = (String) codeAndImage[0];
        BufferedImage image = (BufferedImage) codeAndImage[1];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        String base64Code = ENCODER.encodeBuffer(bytes).replaceAll("\n","").trim();
        Map<String, String> body = new HashMap<>();
        body.put("image", base64Code);
        body.put("code", ENCODER.encodeBuffer(code.getBytes()).replaceAll("\n","").trim());
        return new ResponseBody(body);
    }
}
