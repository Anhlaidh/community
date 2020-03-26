package com.namesapce.community.Exception;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/9 0009 19:13
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode  {


    QUESTION_NOT_FOUND("你找的问题不存在，换个试试？");
    private String message;
     CustomizeErrorCode(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
