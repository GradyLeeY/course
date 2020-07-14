package com.grady.server.service;

import com.grady.server.dto.CourseContentDto;
import com.grady.server.dto.PageDto;

/**
 * @author grady
 * @date 2020 20-7-14 上午11:31
 */
public interface ICourseContentService {

    void list(PageDto pageDto);

    void save(CourseContentDto courseContentDto);

    void delete(String id);

    CourseContentDto findContent(String id);

    int saveContent(CourseContentDto contentDto);
}
