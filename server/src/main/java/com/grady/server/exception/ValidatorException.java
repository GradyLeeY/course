package com.grady.server.exception;

import javax.xml.bind.util.ValidationEventCollector;

/**
 * @Author Grady
 * @Date 2020/6/7 12:40
 * @Version 1.0
 */
public class ValidatorException extends RuntimeException {

    public ValidatorException(String message){
        super(message);
    }
}
