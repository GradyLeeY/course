package com.grady.server.service;

import com.grady.server.dto.PageDto;
import com.grady.server.dto.ResourceDto;

/**
 * @Author Grady
 * @Date 2020/7/29 23:54
 * @Version 1.0
 */
public interface IResourceService {

    void list(PageDto pageDto);

    void save(ResourceDto resourceDto);

    void delete(String id);

    void saveJson(String json);
}
