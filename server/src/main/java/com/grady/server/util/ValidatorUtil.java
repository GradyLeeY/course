package com.grady.server.util;

import com.grady.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

import java.awt.*;

/**
 * @Author Grady
 * @Date 2020/6/7 12:41
 * @Version 1.0
 */
public class ValidatorUtil {

    public static void require(String str,String fieldName){
        if (StringUtils.isEmpty(str)){
            throw new ValidatorException(fieldName+"不能为空");
        }
    }

    public static void length(String str,String fieldName,int min,int max){
        if (StringUtils.isEmpty(str)){
            return;
        }
        int length = 0;
        if (!StringUtils.isEmpty(str)){
            length = str.length();
        }
        if (length < min || length > max){
            throw new ValidatorException(fieldName + "长度" + min + "~" + max + "位");
        }
    }

}
