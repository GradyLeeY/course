package com.grady.server.dto;

import java.util.HashSet;
import java.util.List;

/**
 * @Author Grady
 * @Date 2020/7/27 23:22
 * @Version 1.0
 */
public class LoginUserDto {

    private String id;

    //登录名
    private String loginName;

    //昵称
    private String name;

    private String token;

    //所有资源，用于前端渲染控制
    private List<ResourceDto> resources;

    //所有资源中的请求，用于后端接口的拦截
    private HashSet<String> request;

    public List<ResourceDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDto> resources) {
        this.resources = resources;
    }

    public HashSet<String> getRequest() {
        return request;
    }

    public void setRequest(HashSet<String> request) {
        this.request = request;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginUserDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", resources=").append(resources);
        sb.append(", request=").append(request);
        sb.append('}');
        return sb.toString();
    }
}
