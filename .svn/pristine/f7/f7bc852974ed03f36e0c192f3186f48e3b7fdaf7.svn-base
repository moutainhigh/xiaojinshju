package fengkongweishi.util;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 校验工具类
 *
 * @author huanghengkun
 * @version 1.0.0
 * @date 2018-01-02-上午10:01
 */
public class ValidUtils {

    public static ResponseBody getFirstErrorInfo(BindingResult result) {
        String defaultMessage = result.getAllErrors().get(0).getDefaultMessage();
        ObjectError objectError = result.getAllErrors().get(0);
        BeanWrapperImpl beanWrapperImpl = new BeanWrapperImpl(objectError);
        Object propertyValue = beanWrapperImpl.getPropertyValue("field");
        return new ResponseBody("fail", "8888", defaultMessage, null);
    }
}
