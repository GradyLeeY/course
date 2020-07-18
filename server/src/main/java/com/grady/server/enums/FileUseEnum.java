package com.grady.server.enums;

/**
 * @Author Grady
 * @Date 2020/7/18 10:54
 * @Version 1.0
 */
public enum FileUseEnum {

    COURSE("C", "讲师"),
    TEACHER("T", "课程");

    private String code;

    private String desc;

    FileUseEnum(String code, String desc) {
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
