package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.RoleUser;
import com.grady.server.domain.RoleUserExample;
import com.grady.server.dto.RoleUserDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.RoleUserMapper;
import com.grady.server.service.IRoleUserService;
import com.grady.server.util.CopyUtil;
import com.grady.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
@Service
public class RoleUserServiceImpl implements IRoleUserService {

    @Resource
    private RoleUserMapper roleUserMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleUserExample roleUserExample = new RoleUserExample();
        List<RoleUser> roleUserList = roleUserMapper.selectByExample(roleUserExample);
        PageInfo<RoleUser> pageInfo = new PageInfo<>(roleUserList);
        pageDto.setTotal(pageInfo.getTotal());

        List<RoleUserDto> roleUserDtoList = CopyUtil.copyList(roleUserList,RoleUserDto.class);
        pageDto.setList(roleUserDtoList);
    }

    @Override
    public void save(RoleUserDto roleUserDto){
        RoleUser roleUser = CopyUtil.copy(roleUserDto,RoleUser.class);
        if (StringUtils.isEmpty(roleUserDto.getId())){
            this.insert(roleUser);
        }else {
            this.update(roleUser);
        }
    }

    @Override
    public void delete(String id){
        roleUserMapper.deleteByPrimaryKey(id);
    }

    private void update(RoleUser roleUser) {
        roleUserMapper.updateByPrimaryKey(roleUser);
    }

    private void insert(RoleUser roleUser) {
        roleUser.setId(UuidUtil.getShortUuid());
        roleUserMapper.insert(roleUser);
    }
}