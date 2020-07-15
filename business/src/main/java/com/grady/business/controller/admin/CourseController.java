package com.grady.business.controller.admin;

import com.grady.server.dto.*;
import com.grady.server.service.ICourseCategoryService;
import com.grady.server.service.ICourseContentService;
import com.grady.server.service.ICourseService;
import com.grady.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程";

    @Resource
    private ICourseService icourseService;

    @Resource
    private ICourseCategoryService iCourseCategoryService;

    @Resource
    private ICourseContentService courseContentService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        icourseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto) {
        // 保存校验
        ValidatorUtil.require(courseDto.getName(), "名称");
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);
        LOG.info("categorys ==>{}",courseDto.getCategorys());
        ResponseDto responseDto = new ResponseDto();
        icourseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        icourseService.delete(id);
        return responseDto;
    }

    @PostMapping("/list-category/{courseId}")
    public ResponseDto listCategory(@PathVariable(value = "courseId") String courseId){
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> courseCategoryDtos = iCourseCategoryService.listByCourse(courseId);
        responseDto.setContent(courseCategoryDtos);
        return responseDto;
    }

    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable(value = "id") String id){
        ResponseDto responseDto = new ResponseDto();
        CourseContentDto content = courseContentService.findContent(id);
        responseDto.setContent(content);
        return responseDto;
    }

    @PostMapping("/save-content")
    public ResponseDto saveContent(@RequestBody CourseContentDto contentDto){
        ResponseDto responseDto = new ResponseDto();
        courseContentService.saveContent(contentDto);
        return responseDto;
    }

    @PostMapping("/sort")
    public ResponseDto sort(@RequestBody SortDto sortDto){
        ResponseDto responseDto = new ResponseDto();
        icourseService.sort(sortDto);
        return responseDto;
    }

}
