package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.CourseContentFile;
import com.grady.server.domain.CourseContentFileExample;
import com.grady.server.dto.CourseContentFileDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.CourseContentFileMapper;
import com.grady.server.service.ICourseContentFileService;
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
public class CourseContentFileServiceImpl implements ICourseContentFileService {

    @Resource
    private CourseContentFileMapper courseContentFileMapper;


    @Override
    public List<CourseContentFileDto> list(String courseId) {
        CourseContentFileExample example = new CourseContentFileExample();
        CourseContentFileExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        List<CourseContentFile> courseContentFiles = courseContentFileMapper.selectByExample(example);
        return CopyUtil
                .copyList(courseContentFiles,CourseContentFileDto.class);
    }

    @Override
    public void save(CourseContentFileDto courseContentFileDto){
        CourseContentFile courseContentFile = CopyUtil.copy(courseContentFileDto,CourseContentFile.class);
        if (StringUtils.isEmpty(courseContentFileDto.getId())){
            this.insert(courseContentFile);
        }else {
            this.update(courseContentFile);
        }
    }

    @Override
    public void delete(String id){
        courseContentFileMapper.deleteByPrimaryKey(id);
    }

    private void update(CourseContentFile courseContentFile) {
        courseContentFileMapper.updateByPrimaryKey(courseContentFile);
    }

    private void insert(CourseContentFile courseContentFile) {
        courseContentFile.setId(UuidUtil.getShortUuid());
        courseContentFileMapper.insert(courseContentFile);
    }
}