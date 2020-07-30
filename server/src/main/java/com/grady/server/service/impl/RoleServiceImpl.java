package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Role;
import com.grady.server.domain.RoleExample;
import com.grady.server.dto.RoleDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.RoleMapper;
import com.grady.server.service.IRoleService;
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
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        pageDto.setTotal(pageInfo.getTotal());

        List<RoleDto> roleDtoList = CopyUtil.copyList(roleList,RoleDto.class);
        pageDto.setList(roleDtoList);
    }

    @Override
    public void save(RoleDto roleDto){
        Role role = CopyUtil.copy(roleDto,Role.class);
        if (StringUtils.isEmpty(roleDto.getId())){
            this.insert(role);
        }else {
            this.update(role);
        }
    }

    @Override
    public void delete(String id){
        roleMapper.deleteByPrimaryKey(id);
    }

    private void update(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    private void insert(Role role) {
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }
}