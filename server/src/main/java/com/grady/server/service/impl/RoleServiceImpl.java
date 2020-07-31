package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Role;
import com.grady.server.domain.RoleExample;
import com.grady.server.domain.RoleResource;
import com.grady.server.domain.RoleResourceExample;
import com.grady.server.dto.RoleDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.RoleMapper;
import com.grady.server.mapper.RoleResourceMapper;
import com.grady.server.service.IRoleService;
import com.grady.server.util.CopyUtil;
import com.grady.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource
    private RoleResourceMapper roleResourceMapper;

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

    @Override
    @Transactional
    public void saveResource(RoleDto roleDto){
        String roleId = roleDto.getId();
        List<String> resourceIds = roleDto.getResourceIds();
        //清空库中当前角色下的记录
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        roleResourceExample.createCriteria().andIdEqualTo(roleId);
        roleResourceMapper.deleteByExample(roleResourceExample);

        for (int i = 0; i < resourceIds.size(); i++) {
            RoleResource roleResource = new RoleResource();
            roleResource.setId(UuidUtil.getShortUuid());
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceIds.get(i));
            roleResourceMapper.insert(roleResource);
        }
    }

    @Override
    public List<String> listResource(String roleId){
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        roleResourceExample.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleResource> roleResources = roleResourceMapper.selectByExample(roleResourceExample);
        List<String> resourceIdList = new ArrayList<>();
        for (int i = 0; i < roleResources.size(); i++) {
            String resourceId = roleResources.get(i).getResourceId();
            resourceIdList.add(resourceId);
        }
        return resourceIdList;

    }
    private void update(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    private void insert(Role role) {
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }
}