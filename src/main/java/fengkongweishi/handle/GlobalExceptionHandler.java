package fengkongweishi.handle;

import fengkongweishi.util.FailResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(java.lang.RuntimeException.class)
    @ResponseBody
    public Map handleBizExp(java.lang.Exception ex) {

        HashMap<String, Object> hashMap = new HashMap<>();

        if (ex instanceof FailResponse) {
            hashMap.put("status", "fail");
            hashMap.put("code", ((FailResponse) ex).getCode());
            hashMap.put("message", ((FailResponse) ex).getMessage());
            return hashMap;
        } else if (ex instanceof AccessDeniedException) {
            hashMap.put("status", "fail");
            hashMap.put("code", 9997);
            hashMap.put("message", "无权限");
            return hashMap;
        } else if(ex instanceof DataIntegrityViolationException){
            hashMap.put("status", "fail");
            hashMap.put("code", 9998);
            hashMap.put("message", "违反唯一索引");
            return hashMap;
        } else {
            ex.printStackTrace();
            hashMap.put("status", "fail");
            hashMap.put("code", 9999);
            hashMap.put("message", ex.getMessage());
            return hashMap;
        }


    }
}