package fengkongweishi.util;

import fengkongweishi.enums.ExceptionEnum;

public class FailResponse extends RuntimeException {

    private int code;
    private String message;

    public FailResponse(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public FailResponse(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public FailResponse(ExceptionEnum exceptionEnum, String detailInfo) {
        super(exceptionEnum.getMessage() + ":" + detailInfo);
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage() + ":" + detailInfo;
    }

    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
