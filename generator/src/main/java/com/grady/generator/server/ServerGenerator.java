package com.grady.generator.server;

import com.grady.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @Author Grady
 * @Date 2020/6/8 14:08
 * @Version 1.0
 */
public class ServerGenerator {
    static String toPath = "generator\\src\\main\\java\\com\\grady\\generator\\test\\";

    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        FreemarkerUtil.generator(toPath+"Test.java");
    }

}
