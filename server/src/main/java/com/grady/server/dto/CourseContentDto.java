package com.grady.server.dto;


public class CourseContentDto {

    /**
    * 课程id
    */
    private String id;

    /**
    * 课程内容
    */
    private String content;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getContent() {
    return content;
    }

    public void setContent(String content) {
    this.content = content;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseContentDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

}