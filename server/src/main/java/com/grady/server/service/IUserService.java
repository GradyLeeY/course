package com.grady.server.service;

import com.grady.server.domain.User;
import com.grady.server.dto.PageDto;
import com.grady.server.dto.UserDto;

/**
 * @Author Grady
 * @Date 2020/7/27 20:21
 * @Version 1.0
 */
public interface IUserService {

    void list(PageDto pageDto);

    void save(UserDto userDto);

    void delete(String id);

    User selectByLoginname(String loginName);

    void savePassword(UserDto userDto);
}
