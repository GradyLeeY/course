package com.grady.generator.server;

import com.grady.generator.util.DbUtil;
import com.grady.generator.util.Field;
import com.grady.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

/**
 * @Author Grady
 * @Date 2020/6/8 14:08
 * @Version 1.0
 */
public class ServerGenerator {
    static String MODULE = "business";
    static String toDtoPath = "server\\src\\main\\java\\com\\grady\\server\\dto\\";
    static String toServicePath = "server\\src\\main\\java\\com\\grady\\server\\service\\impl\\";
    static String toControllerPath = MODULE+"\\src\\main\\java\\com\\grady\\"+MODULE+"\\controller\\admin\\";

    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String,Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("tableNameCn",tableNameCn);
        map.put("module",MODULE);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(toDtoPath + Domain + "Dto.java", map);
        //FreemarkerUtil.initConfig("service.ftl");
        //FreemarkerUtil.generator(toServicePath+Domain+"ServiceImpl.java",map);
       /* FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);*/
    }

    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }

}
