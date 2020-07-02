package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Section;
import com.grady.server.domain.SectionExample;
import com.grady.server.dto.SectionDto;
import com.grady.server.dto.PageDto;
import com.grady.server.dto.SectionPageDto;
import com.grady.server.mapper.SectionMapper;
import com.grady.server.service.ICourseService;
import com.grady.server.service.ISectionService;
import com.grady.server.util.CopyUtil;
import com.grady.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource
    private ICourseService iCourseService;

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

    /**
     * 保存，id有值时更新，无值时新增
     * @param sectionDto
     */
    @Override
    @Transactional
    public void save(SectionDto sectionDto){
        /**
         * 注解不生效的原因：1 异常为Exception需要在注解中加rollbackFor = Exception.class，自定义异常一般选择集成RuntimeException
         * 2 如果不抛异常 而是使用try catch 也是不起作用的
         * 3 同一个类的内部方法互相调用，methodA 调用methodB，methodB的事务不起作用，spring的事务处理时利用AOP生成代理类，内部方法调用时不经过
         * 代理类，所以事务不生效
        */
        Section section = CopyUtil.copy(sectionDto,Section.class);
        if (StringUtils.isEmpty(sectionDto.getId())){
            this.insert(section);
        }else {
            this.update(section);
        }
        iCourseService.updateTime(sectionDto.getCourseId());
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