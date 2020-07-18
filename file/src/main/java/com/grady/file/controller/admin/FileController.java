package com.grady.file.controller.admin;

import com.grady.server.dto.FileDto;
import com.grady.server.dto.PageDto;
import com.grady.server.dto.ResponseDto;
import com.grady.server.service.IFileService;
import com.grady.server.util.ValidatorUtil;
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

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto) {
        // 保存校验
        ValidatorUtil.require(fileDto.getPath(), "相对路径");
        ValidatorUtil.length(fileDto.getPath(), "相对路径", 1, 100);
        ValidatorUtil.length(fileDto.getName(), "文件名", 1, 100);
        ValidatorUtil.length(fileDto.getSuffix(), "后缀", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        iFileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        iFileService.delete(id);
        return responseDto;
    }
}
