package com.grady.server.service;

import com.grady.server.domain.Course;
import com.grady.server.dto.CourseDto;
import com.grady.server.dto.PageDto;

/**
 * @Author Grady
 * @Date 2020/6/15 20:23
 * @Version 1.0
 */
public interface ICourseService {

    void list(PageDto pageDto);
    void save(CourseDto courseDto);
    void delete(String id);
}
