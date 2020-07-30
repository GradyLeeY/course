package com.grady.server.service;

import com.grady.server.dto.PageDto;
import com.grady.server.dto.RoleDto;

/**
 * @Author Grady
 * @Date 2020/7/30 23:04
 * @Version 1.0
 */
public interface IRoleService {

    public void list(PageDto pageDto);

    public void save(RoleDto roleDto);

    public void delete(String id);
}
