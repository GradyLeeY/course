package com.grady.server.service;

import com.grady.server.dto.PageDto;
import com.grady.server.dto.RoleDto;
import com.grady.server.dto.RoleUserDto;

/**
 * @Author Grady
 * @Date 2020/7/31 22:07
 * @Version 1.0
 */
public interface IRoleUserService {

    public void list(PageDto pageDto);

    public void save(RoleUserDto roleUserDto);

    public void delete(String id);


}
