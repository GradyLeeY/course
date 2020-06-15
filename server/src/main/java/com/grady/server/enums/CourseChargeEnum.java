package com.grady.server.enums;

/**
 * @Author Grady
 * @Date 2020/6/15 20:48
 * @Version 1.0
 */
public enum CourseChargeEnum {

    CHARGE("C", "收费"),
    FREE("F", "免费");

    private String code;

    private String desc;

    CourseChargeEnum(String code, String desc) {
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
