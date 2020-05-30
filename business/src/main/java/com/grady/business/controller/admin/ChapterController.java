package com.grady.business.controller.admin;

import com.grady.server.dto.ChapterDto;
import com.grady.server.dto.PageDto;
import com.grady.server.service.IChapterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Grady
 * @Date 2020/5/24 14:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private IChapterService iChapterService;

    @RequestMapping("/list")
    //PageDto pageDto 前端传参有两种方式，一种是表单提交，一种是json流的方式
    //vue 和angular默认是使用json流的方式所以要加上RequestBody的注解
    public PageDto list(@RequestBody PageDto pageDto){
         iChapterService.list(pageDto);
         return pageDto;
    }
}
