package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Section;
import com.grady.server.domain.SectionExample;
import com.grady.server.dto.SectionDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.SectionMapper;
import com.grady.server.service.ISectionService;
import com.grady.server.util.CopyUtil;
import com.grady.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

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
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);

        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());
        /*for (int i = 0, l = sectionList.size(); i < l; i++) {
            Section section = sectionList.get(i);
            SectionDto sectionDto = new SectionDto();
            BeanUtils.copyProperties(section, sectionDto);
            sectionDtoList.add(sectionDto);
        }*/
        List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList,SectionDto.class);
        pageDto.setList(sectionDtoList);
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
        sectionMapper.updateByPrimaryKey(section);
    }

    private void insert(Section section) {
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }
}
