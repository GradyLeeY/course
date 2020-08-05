package com.grady.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.User;
import com.grady.server.domain.UserExample;
import com.grady.server.dto.LoginUserDto;
import com.grady.server.dto.ResourceDto;
import com.grady.server.dto.UserDto;
import com.grady.server.dto.PageDto;
import com.grady.server.exception.BusinessException;
import com.grady.server.exception.BusinessExceptionCode;
import com.grady.server.mapper.UserMapper;
import com.grady.server.service.IUserService;
import com.grady.server.util.CopyUtil;
import com.grady.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        pageDto.setTotal(pageInfo.getTotal());

        List<UserDto> userDtoList = CopyUtil.copyList(userList,UserDto.class);
        pageDto.setList(userDtoList);
    }

    @Override
    public void save(UserDto userDto){
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        User user = CopyUtil.copy(userDto,User.class);
        if (StringUtils.isEmpty(userDto.getId())){
            this.insert(user);
        }else {
            this.update(user);
        }
    }

    public User selectByLoginname(String loginName){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }

    public LoginUserDto login(UserDto userDto){
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        User userDb = selectByLoginname(userDto.getLoginName());
        if (userDb == null){
            throw new BusinessException(BusinessExceptionCode.LOGIN_ERROR);
        }else {
            if (userDb.getPassword().equals(userDto.getPassword())){
                // 登录成功
                LoginUserDto loginUserDto = CopyUtil.copy(userDb, LoginUserDto.class);
                setAuth(loginUserDto);
                return loginUserDto;
            }else {
                logger.info("数据库密码：{}，输入密码:{}",userDb.getPassword(),userDto.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_ERROR);
            }
        }
    }

    /**
     * 为登录用户读取权限
     * @param loginUserDto
     */
    private void setAuth(LoginUserDto loginUserDto) {
        List<ResourceDto> resources = userMapper.findResources(loginUserDto.getId());
        loginUserDto.setResources(resources);
        //整理所有权限的请求，用于接口拦截
        HashSet<String> requestSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(resources)){
            for (int i = 0; i < resources.size(); i++) {
                ResourceDto resourceDto = resources.get(i);
                String arrayString = resourceDto.getRequest();
                List<String> requestList = JSON.parseArray(arrayString, String.class);
                if (!CollectionUtils.isEmpty(requestList)) {
                    requestSet.addAll(requestList);
                }
            }
        }
        logger.info("有权限的请求:{}",requestSet);
        loginUserDto.setRequests(requestSet);
    }

    public void savePassword(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(String id){
        userMapper.deleteByPrimaryKey(id);
    }


    private void update(User user) {
        user.setPassword(null);
        //有值就更新，没值就不更新
        userMapper.updateByPrimaryKeySelective(user);
    }

    private void insert(User user) {
        user.setId(UuidUtil.getShortUuid());
        User userDb = selectByLoginname(user.getLoginName());
        if(userDb != null){
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userMapper.insert(user);
    }

}