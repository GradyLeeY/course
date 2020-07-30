package com.grady.server.service;

import com.grady.server.dto.PageDto;
import com.grady.server.dto.RoleResourceDto;

/**
 * @Author Grady
 * @Date 2020/7/30 23:13
 * @Version 1.0
 */
public interface IRoleResourceService {

    public void list(PageDto pageDto);

    public void save(RoleResourceDto roleResourceDto);

    public void delete(String id);
}
