package com.grady.generator.test;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * @Author Grady
 * @Date 2020/6/7 21:23
 * @Version 1.0
 */
public class TestUtil {

    static String ftlPath = "generator\\src\\main\\java\\com\\grady\\generator\\test\\";
    static String toPath = "generator\\src\\main\\java\\com\\grady\\generator\\test\\";
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        Template temp = cfg.getTemplate("test.ftl");

        FileWriter fw = new FileWriter(toPath + "Test.java");
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(null, bw);
        bw.flush();
        fw.close();
    }
}
