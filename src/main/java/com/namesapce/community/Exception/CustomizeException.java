package com.namesapce.community.Exception;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/26 0026 18:52
 */
public class CustomizeException extends RuntimeException{
    private String message;



    public CustomizeException(String message) {
        this.message = message;
    }
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
