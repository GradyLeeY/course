package com.grady.server.service.impl;

import com.alibaba.fastjson.JSON;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    private static Logger LOG = LoggerFactory.getLogger(ResourceServiceImpl.class);

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

    @Transactional
    public void saveJson(String json){
        List<ResourceDto> jsonList = JSON.parseArray(json,ResourceDto.class);
        List<ResourceDto> list = new ArrayList<>();

        if (!CollectionUtils.isEmpty(jsonList)){
            for (ResourceDto dto:jsonList
                 ) {
                dto.setParent("");
                addList(list,dto);
            }
        }
        LOG.info("共{}条", list.size());
        resourceMapper.deleteByExample(null);

        for (int i = 0; i < list.size(); i++) {
            this.insert(CopyUtil.copy(list.get(i), Resource.class));
        }
    }

    /**
     * 按约定将列表转成树
     * 要求：ID要正序排列
     * @return
     */
    public List<ResourceDto> loadTree() {
        ResourceExample example = new ResourceExample();
        example.setOrderByClause("id asc");
        List<Resource> resourceList = resourceMapper.selectByExample(example);
        List<ResourceDto> resourceDtoList = CopyUtil.copyList(resourceList, ResourceDto.class);
        for (int i = resourceDtoList.size() - 1; i >= 0; i--) {
            // 当前要移动的记录
            ResourceDto child = resourceDtoList.get(i);

            // 如果当前节点没有父节点，则不用往下了
            if (StringUtils.isEmpty(child.getParent())) {
                continue;
            }
            // 查找父节点
            for (int j = i - 1; j >= 0; j--) {
                ResourceDto parent = resourceDtoList.get(j);
                if (child.getParent().equals(parent.getId())) {
                    if (CollectionUtils.isEmpty(parent.getChildren())) {
                        parent.setChildren(new ArrayList<>());
                    }
                    // 添加到最前面，否则会变成倒序，因为循环是从后往前循环的
                    parent.getChildren().add(0, child);

                    // 子节点找到父节点后，删除列表中的子节点
                    resourceDtoList.remove(child);
                }
            }
        }
        return resourceDtoList;
    }
    private void addList(List<ResourceDto> list, ResourceDto dto) {
        list.add(dto);
        if (!CollectionUtils.isEmpty(dto.getChildren())) {
            for (ResourceDto d: dto.getChildren()) {
                d.setParent(dto.getId());
                addList(list, d);
            }
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
        resourceMapper.insert(resource);
    }
}