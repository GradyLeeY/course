package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.grady.server.domain.Chapter;
import com.grady.server.domain.ChapterExample;
import com.grady.server.dto.ChapterDto;
import com.grady.server.mapper.ChapterMapper;
import com.grady.server.service.IChapterService;
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

    public List<ChapterDto> getAllChapter(){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(2,1);
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (int i = 0, l = chapterList.size(); i < l; i++) {
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }

}