package com.grady.business.controller.admin;

import com.grady.server.dto.ChapterDto;
import com.grady.server.service.IChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Grady
 * @Date 2020/5/24 14:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private IChapterService iChapterService;

    @RequestMapping("/list")
    public List<ChapterDto> getAllChapter(){
        return iChapterService.getAllChapter();
    }
}
