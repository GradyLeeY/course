package com.grady.business.controller.admin;

import com.grady.server.dto.SectionDto;
import com.grady.server.dto.PageDto;
import com.grady.server.dto.ResponseDto;
import com.grady.server.service.ISectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Grady
 * @Date 2020/5/24 14:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/section")
public class SectionController {

    public static final String BUSINESS_NAME = "小节";

    @Resource
    private ISectionService iSectionService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    //PageDto pageDto 前端传参有两种方式，一种是表单提交，一种是json流的方式 vue 和angular默认是使用json流的方式所以要加上RequestBody的注解
    public ResponseDto list(@RequestBody PageDto pageDto){
        iSectionService.list(pageDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseDto save(@RequestBody SectionDto sectionDto){

        ResponseDto responseDto = new ResponseDto();
        iSectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        iSectionService.delete(id);
        return responseDto;
    }
}
