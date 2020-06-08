package com.grady.generator.server;

import com.grady.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Grady
 * @Date 2020/6/8 14:08
 * @Version 1.0
 */
public class ServerGenerator {
    static String toServicePath = "server\\src\\main\\java\\com\\grady\\server\\service\\impl\\";
    static String toControllerPath = "business\\src\\main\\java\\com\\grady\\business\\controller\\admin\\";

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        Map<String,Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        //FreemarkerUtil.initConfig("service.ftl");
        //FreemarkerUtil.generator(toServicePath+Domain+"ServiceImpl.java",map);
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);
    }

}
