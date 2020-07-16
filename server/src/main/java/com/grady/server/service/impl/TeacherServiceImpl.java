package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Teacher;
import com.grady.server.domain.TeacherExample;
import com.grady.server.dto.TeacherDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.TeacherMapper;
import com.grady.server.service.ITeacherService;
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
public class TeacherServiceImpl implements ITeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());

        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList,TeacherDto.class);
        pageDto.setList(teacherDtoList);
    }

    @Override
    public void save(TeacherDto teacherDto){
        Teacher teacher = CopyUtil.copy(teacherDto,Teacher.class);
        if (StringUtils.isEmpty(teacherDto.getId())){
            this.insert(teacher);
        }else {
            this.update(teacher);
        }
    }

    @Override
    public void delete(String id){
        teacherMapper.deleteByPrimaryKey(id);
    }

    public List<TeacherDto> all(){
        TeacherExample example = new TeacherExample();
        List<Teacher> teachers = teacherMapper.selectByExample(example);
        return CopyUtil.copyList(teachers,TeacherDto.class);
    }
    private void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    private void insert(Teacher teacher) {
        teacher.setId(UuidUtil.getShortUuid());
        teacherMapper.insert(teacher);
    }
}