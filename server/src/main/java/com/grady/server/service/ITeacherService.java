package com.grady.server.service;

import com.grady.server.dto.PageDto;
import com.grady.server.dto.TeacherDto;

/**
 * @author grady
 * @date 2020 20-7-16 下午4:03
 */
public interface ITeacherService {

    void list(PageDto pageDto);

    void save(TeacherDto teacherDto);

    void delete(String id);
}
