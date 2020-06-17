package com.grady.server.service;

import com.grady.server.dto.ChapterDto;
import com.grady.server.dto.ChapterPageDto;
import com.grady.server.dto.PageDto;

import java.util.List;

/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
public interface IChapterService {

    void list(ChapterPageDto chapterPageDto);

    void save(ChapterDto chapterDto);

    void delete(String id);
}
