package com.grady.server.service;

import com.grady.server.domain.File;
import com.grady.server.dto.FileDto;
import com.grady.server.dto.PageDto;

/**
 * @Author Grady
 * @Date 2020/7/18 15:50
 * @Version 1.0
 */
public interface IFileService {

    void list(PageDto pageDto);

    void save(FileDto fileDto);

    void delete(String id);

    File selectByKey(String key);
}
