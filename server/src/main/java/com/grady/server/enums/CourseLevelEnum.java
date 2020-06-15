package com.grady.server.enums;

/**
 * @Author Grady
 * @Date 2020/6/15 20:49
 * @Version 1.0
 */
public enum CourseLevelEnum {

    ONE("1", "初级"),
    TWO("2", "中级"),
    THREE("3", "高级");

    private String code;

    private String desc;

    CourseLevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
