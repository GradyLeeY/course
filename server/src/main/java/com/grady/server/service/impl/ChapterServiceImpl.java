package com.grady.server.service.impl;

import com.grady.server.domain.Chapter;
import com.grady.server.mapper.ChapterMapper;
import com.grady.server.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
@Service
public class ChapterServiceImpl implements IChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public String selectID(String id){
        Chapter chapter = chapterMapper.selectByPrimaryKey(id);
        return chapter.getName();
    }

}
