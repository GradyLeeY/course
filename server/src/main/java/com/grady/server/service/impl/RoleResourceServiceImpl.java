package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.RoleResource;
import com.grady.server.domain.RoleResourceExample;
import com.grady.server.dto.RoleResourceDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.RoleResourceMapper;
import com.grady.server.service.IRoleResourceService;
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
public class RoleResourceServiceImpl implements IRoleResourceService {

    @Resource
    private RoleResourceMapper roleResourceMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        List<RoleResource> roleResourceList = roleResourceMapper.selectByExample(roleResourceExample);
        PageInfo<RoleResource> pageInfo = new PageInfo<>(roleResourceList);
        pageDto.setTotal(pageInfo.getTotal());

        List<RoleResourceDto> roleResourceDtoList = CopyUtil.copyList(roleResourceList,RoleResourceDto.class);
        pageDto.setList(roleResourceDtoList);
    }

    @Override
    public void save(RoleResourceDto roleResourceDto){
        RoleResource roleResource = CopyUtil.copy(roleResourceDto,RoleResource.class);
        if (StringUtils.isEmpty(roleResourceDto.getId())){
            this.insert(roleResource);
        }else {
            this.update(roleResource);
        }
    }

    @Override
    public void delete(String id){
        roleResourceMapper.deleteByPrimaryKey(id);
    }

    private void update(RoleResource roleResource) {
        roleResourceMapper.updateByPrimaryKey(roleResource);
    }

    private void insert(RoleResource roleResource) {
        roleResource.setId(UuidUtil.getShortUuid());
        roleResourceMapper.insert(roleResource);
    }
}