package com.namesapce.community.Exception;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/9 0009 19:13
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {


    QUESTION_NOT_FOUND(2001, "你找的问题不存在，换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题,换一个试试?"),
    NO_LOGIN(2003, "当前操作需要登录,请登陆后再试"),
    SYS_ERROR(2004,"系统错误" ), TYPE_PARAM_WRONG(2005, "评论类型错误或不存在");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
