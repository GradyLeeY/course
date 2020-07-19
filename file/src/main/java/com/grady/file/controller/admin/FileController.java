package com.grady.file.controller.admin;

import com.grady.server.dto.PageDto;
import com.grady.server.dto.ResponseDto;
import com.grady.server.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Grady
 * @Date 2020/7/18 15:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    public static final String BUSINESS_NAME = "文件";

    @Resource
    private IFileService iFileService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        iFileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

}
