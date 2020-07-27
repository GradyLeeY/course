package com.grady.server.exception;

/**
 * @Author Grady
 * @Date 2020/7/27 21:05
 * @Version 1.0
 */
public enum  BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_ERROR("用户名或密码不存在"),
            ;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    BusinessExceptionCode(String desc){
        this.desc = desc;
    }

}
