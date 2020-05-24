package com.grady.server.service;

import com.grady.server.dto.ChapterDto;

import java.util.List;

/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
public interface IChapterService {

    List<ChapterDto> getAllChapter();
}
