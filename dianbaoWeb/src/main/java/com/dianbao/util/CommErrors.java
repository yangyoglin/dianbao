package com.dianbao.util;

/**
 * @desc:: 错误码定义
 * @author: YYL
 * @date: 18/12/2017 10:35 AM
 *
 */
public enum CommErrors {
	
    //COMM常用的定义
    API_SUCCESS(1, "成功"),
    API_ERROR(-1, "系统繁忙，请稍后重试"),
    API_NOT_EXIST(10001, "请求接口(%s)不存在"),
    LOGIN_AUTH_FAIR(401, "登录已过期"),
    LOGIN_ISNO(402,"没有该账号"),
    LOGIN_ISPWD(403,"密码错误"),
    
    WX_USER_NOT_PERM(103, "帐号没有权限访问"),
    
    
    ADMIN_PARAMS_NULL(30002, "缺少必填参数"),
    ADMIN_START_TIME_NULL(40003, "查询开始时间不能为空"),
    ADMIN_END_TIME_NULL(40004, "查询结束时间不能为空"),
    ADMIN_STATUS_NULL(40007, "状态不能为空"),
    MISSING_PARAM(80000, "缺少参数"),
    //monitor接口异常
    MON_ERROR(30001, "参数错误"),
    IMAGES_ERROR(30003, "请上传jpg、jpeg、png、gif类型图片"),

    ;

    private int errorCode;
    private String errorMsg;

    CommErrors(int code, String message) {
        this.errorCode = code;
        this.errorMsg = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
