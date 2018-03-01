package fengkongweishi.util;

/**
 * 响应封装类
 *
 * @author huanghengkun
 * @date 2018/01/08
 */
public class ResponseBody {

    private String status;
    private String code;
    private String message;
    private Object data;

    public ResponseBody(Object data) {
        this.status = "success";
        this.code = "0";
        this.message = "";
        this.data = data;
    }

    public ResponseBody() {
        this.status = "success";
        this.code = "0";
        this.message = "";
        this.data = null;
    }

    public ResponseBody(String status, String code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
