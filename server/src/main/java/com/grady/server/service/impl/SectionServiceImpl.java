package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Section;
import com.grady.server.domain.SectionExample;
import com.grady.server.dto.SectionDto;
import com.grady.server.dto.PageDto;
import com.grady.server.dto.SectionPageDto;
import com.grady.server.mapper.SectionMapper;
import com.grady.server.service.ISectionService;
import com.grady.server.util.CopyUtil;
import com.grady.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
@Service
public class SectionServiceImpl implements ISectionService {

    @Resource
    private SectionMapper sectionMapper;

    @Override
    public void list(SectionPageDto sectionPageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(sectionPageDto.getPage(),sectionPageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        SectionExample.Criteria criteria = sectionExample.createCriteria();
        if (!StringUtils.isEmpty(sectionPageDto.getCourseId())){
            criteria.andCourseIdEqualTo(sectionPageDto.getCourseId());
        }
        if (!StringUtils.isEmpty(sectionPageDto.getChapterId())){
            criteria.andChapterIdEqualTo(sectionPageDto.getChapterId());
        }
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        sectionPageDto.setTotal(pageInfo.getTotal());

        List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList,SectionDto.class);
        sectionPageDto.setList(sectionDtoList);
    }

    @Override
    public void save(SectionDto sectionDto){
        Section section = CopyUtil.copy(sectionDto,Section.class);
        if (StringUtils.isEmpty(sectionDto.getId())){
            this.insert(section);
        }else {
            this.update(section);
        }
    }

    @Override
    public void delete(String id){
        sectionMapper.deleteByPrimaryKey(id);
    }

    private void update(Section section) {
        section.setUpdatedAt(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }

    private void insert(Section section) {
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }
}