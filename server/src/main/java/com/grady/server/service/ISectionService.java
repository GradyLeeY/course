package com.grady.server.service;

import com.grady.server.dto.PageDto;
import com.grady.server.dto.SectionDto;
import com.grady.server.dto.SectionPageDto;

/**
 * @Author Grady
 * @Date 2020/6/8 14:29
 * @Version 1.0
 */
public interface ISectionService {

    void list(SectionPageDto sectionPageDto);

    void save(SectionDto sectionDto);

    void delete(String id);
}
