package com.grady.server.service;

import com.grady.server.dto.CourseContentFileDto;

import java.util.List;

/**
 * @Author Grady
 * @Date 2020/7/20 18:48
 * @Version 1.0
 */
public interface ICourseContentFileService {

    List<CourseContentFileDto> list(String courseId);

    void save(CourseContentFileDto courseContentFileDto);

    void delete(String id);

}
