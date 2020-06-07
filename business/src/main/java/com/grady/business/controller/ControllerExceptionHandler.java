package com.grady.business.controller;

import com.grady.server.dto.ResponseDto;
import com.grady.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**Contro 是controller增强器，可以对controller做统一处理，如异常处理，数据处理等
 * @Author Grady
 * @Date 2020/6/7 12:39
 * @Version 1.0
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExeptionHandler(ValidatorException e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        logger.warn(e.getMessage());
        responseDto.setMessage("请求参数异常");
        return responseDto;
    }
}
