package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.Chapter;
import com.grady.server.domain.ChapterExample;
import com.grady.server.dto.ChapterDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.ChapterMapper;
import com.grady.server.service.IChapterService;
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
public class ChapterServiceImpl implements IChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);

        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
        /*for (int i = 0, l = chapterList.size(); i < l; i++) {
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }*/
        List<ChapterDto> chapterDtoList = CopyUtil.copyList(chapterList,ChapterDto.class);
        pageDto.setList(chapterDtoList);
    }

    @Override
    public void save(ChapterDto chapterDto){
        Chapter chapter = CopyUtil.copy(chapterDto,Chapter.class);
        if (StringUtils.isEmpty(chapterDto.getId())){
            this.insert(chapter);
        }else {
            this.update(chapter);
        }
    }

    @Override
    public void delete(String id){
        chapterMapper.deleteByPrimaryKey(id);
    }

    private void update(Chapter chapter) {
        chapterMapper.updateByPrimaryKey(chapter);
    }

    private void insert(Chapter chapter) {
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }
}
