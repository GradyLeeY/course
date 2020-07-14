package com.grady.server.service;
import com.grady.server.dto.CategoryDto;
import com.grady.server.dto.CourseCategoryDto;
import com.grady.server.dto.PageDto;

import java.util.List;

/**
 * @Author Grady
 * @Date 2020/7/13 21:15
 * @Version 1.0
 */
public interface ICourseCategoryService {

    void list(PageDto pageDto);

    void save(CourseCategoryDto courseCategoryDto);

    void delete(String id);

    void saveBatch(String courseId, List<CategoryDto> categoryDtos);

    List<CourseCategoryDto> listByCourse(String courseId);

}
