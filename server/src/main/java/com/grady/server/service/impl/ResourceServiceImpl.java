package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Resource;
import com.grady.server.domain.ResourceExample;
import com.grady.server.dto.ResourceDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.ResourceMapper;
import com.grady.server.service.IResourceService;
import com.grady.server.util.CopyUtil;
import com.grady.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ResourceExample resourceExample = new ResourceExample();
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        pageDto.setTotal(pageInfo.getTotal());

        List<ResourceDto> resourceDtoList = CopyUtil.copyList(resourceList,ResourceDto.class);
        pageDto.setList(resourceDtoList);
    }

    @Override
    public void save(ResourceDto resourceDto){
        Resource resource = CopyUtil.copy(resourceDto,Resource.class);
        if (StringUtils.isEmpty(resourceDto.getId())){
            this.insert(resource);
        }else {
            this.update(resource);
        }
    }

    @Override
    public void delete(String id){
        resourceMapper.deleteByPrimaryKey(id);
    }

    private void update(Resource resource) {
        resourceMapper.updateByPrimaryKey(resource);
    }

    private void insert(Resource resource) {
        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }
}