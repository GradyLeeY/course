package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Course;
import com.grady.server.domain.CourseExample;
import com.grady.server.dto.CourseDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.CourseMapper;
import com.grady.server.service.ICourseService;
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
public class CourseServiceImpl implements ICourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        List<Course> courseList = courseMapper.selectByExample(courseExample);
            courseExample.setOrderByClause("sort asc");
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseDto> courseDtoList = CopyUtil.copyList(courseList,CourseDto.class);
        pageDto.setList(courseDtoList);
    }

    @Override
    public void save(CourseDto courseDto){
        Course course = CopyUtil.copy(courseDto,Course.class);
        if (StringUtils.isEmpty(courseDto.getId())){
            this.insert(course);
        }else {
            this.update(course);
        }
    }

    @Override
    public void delete(String id){
        courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateTime(String courseId){
        return courseMapper.updateTime(courseId);
    }

    private void update(Course course) {
            course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    private void insert(Course course) {
        Date now = new Date();
            course.setCreatedAt(now);
            course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

}