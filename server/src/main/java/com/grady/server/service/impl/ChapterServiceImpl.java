package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Chapter;
import com.grady.server.domain.ChapterExample;
import com.grady.server.dto.ChapterDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.ChapterMapper;
import com.grady.server.service.IChapterService;
import com.grady.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
@Service
public class ChapterServiceImpl implements IChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
        for (int i = 0, l = chapterList.size(); i < l; i++) {
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        pageDto.setList(chapterDtoList);
    }

    public void save(ChapterDto chapterDto){
        chapterDto.setId(UuidUtil.getShortUuid());
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterDto,chapter);
        chapterMapper.insert(chapter);
    }
}
