package com.grady.business.controller.admin;

import com.grady.server.dto.${Domain}Dto;
import com.grady.server.dto.PageDto;
import com.grady.server.dto.ResponseDto;
import com.grady.server.service.I${Domain}Service;
import com.grady.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Grady
 * @Date 2020/5/24 14:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    public static final String BUSINESS_NAME = "大章";

    @Resource
    private I${Domain}Service i${Domain}Service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    //PageDto pageDto 前端传参有两种方式，一种是表单提交，一种是json流的方式 vue 和angular默认是使用json流的方式所以要加上RequestBody的注解
    public ResponseDto list(@RequestBody PageDto pageDto){
        i${Domain}Service.list(pageDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto){
        ValidatorUtil.require(${domain}Dto.getName(),"课程名称");
        ValidatorUtil.require(${domain}Dto.getCourseId(),"课程id");
        ValidatorUtil.length(${domain}Dto.getCourseId(),"课程id",4,8);
        ResponseDto responseDto = new ResponseDto();
        i${Domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        i${Domain}Service.delete(id);
        return responseDto;
    }
}
