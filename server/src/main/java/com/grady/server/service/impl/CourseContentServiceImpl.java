package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.CourseContent;
import com.grady.server.domain.CourseContentExample;
import com.grady.server.dto.CourseContentDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.CourseContentMapper;
import com.grady.server.service.ICourseContentService;
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
public class CourseContentServiceImpl implements ICourseContentService {

    @Resource
    private CourseContentMapper courseContentMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseContentExample courseContentExample = new CourseContentExample();
        List<CourseContent> courseContentList = courseContentMapper.selectByExample(courseContentExample);
        PageInfo<CourseContent> pageInfo = new PageInfo<>(courseContentList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseContentDto> courseContentDtoList = CopyUtil.copyList(courseContentList,CourseContentDto.class);
        pageDto.setList(courseContentDtoList);
    }

    @Override
    public void save(CourseContentDto courseContentDto){
        CourseContent courseContent = CopyUtil.copy(courseContentDto,CourseContent.class);
        if (StringUtils.isEmpty(courseContentDto.getId())){
            this.insert(courseContent);
        }else {
            this.update(courseContent);
        }
    }

    @Override
    public void delete(String id){
        courseContentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存课程内容包括新增和修改
     * @param contentDto
     * @return
     */
    public int saveContent(CourseContentDto contentDto){
        CourseContent courseContent = CopyUtil.copy(contentDto,CourseContent.class);
        int result = courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);
        if (result == 0){
            result = courseContentMapper.insert(courseContent);
        }
        return result;

    }
    /**
     * 查找课程内容
     * @param id
     * @return
     */
    @Override
    public CourseContentDto findContent(String id){
        CourseContent courseContent = courseContentMapper.selectByPrimaryKey(id);
        if (StringUtils.isEmpty(courseContent)){
            return null;
        }
        return CopyUtil.copy(courseContent,CourseContentDto.class);
    }


    private void update(CourseContent courseContent) {
        courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);
    }

    private void insert(CourseContent courseContent) {
        courseContent.setId(UuidUtil.getShortUuid());
        courseContentMapper.insert(courseContent);
    }
}