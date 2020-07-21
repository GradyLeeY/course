package com.grady.file.controller.admin;

import com.grady.server.dto.FileDto;
import com.grady.server.dto.ResponseDto;
import com.grady.server.enums.FileUseEnum;
import com.grady.server.service.IFileService;
import com.grady.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

/**
 * @Author Grady
 * @Date 2020/7/17 12:51
 * @Version 1.0
 */
@RequestMapping("/admin")
@RestController
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
    public static final String BUSINESS_NAME = "文件上传";

    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @Value("${file.path}")
    private String FILE_PATH;

    @Resource
    private IFileService iFileService;

    @PostMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile shard,String use,Integer shardIndex,Integer shardSize,
                              Integer shardTotal,String name,String suffix,Integer size,String key) throws IOException {
        LOG.info(shard.getOriginalFilename());
        LOG.info(String.valueOf(shard.getSize()));

        // 保存文件到本地
        FileUseEnum fileUseEnum = FileUseEnum.getByCode(use);

        String dir = fileUseEnum.name().toLowerCase();
        File fullDir = new File(dir);
        if (!fullDir.exists()){
            fullDir.mkdir();
        }

        String path = dir + File.separator + key + "." + suffix;
        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);
        shard.transferTo(dest);
        LOG.info(dest.getAbsolutePath());

        LOG.info("保存文件记录开始");
        FileDto fileDto = new FileDto();
        fileDto.setPath(path);
        fileDto.setName(name);
        fileDto.setSuffix(suffix);
        fileDto.setShardIndex(shardIndex);
        fileDto.setUse(use);
        fileDto.setSize(size);
        fileDto.setShardSize(shardSize);
        fileDto.setShardTotal(shardTotal);
        fileDto.setKey(key);
        iFileService.save(fileDto);
        LOG.info("保存文件记录结束");
        fileDto.setPath(FILE_DOMAIN+path);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(fileDto);
        return responseDto;
    }

    @GetMapping("/merge")
    public ResponseDto merge() throws Exception  {
        File newFile = new File(FILE_PATH+"/course/test123.mp4");
        FileOutputStream outputStream = new FileOutputStream(newFile,true);
        FileInputStream inputStream = null;

        byte[] bytes = new byte[1024*10*1024];
        int len;
        try {
            inputStream = new FileInputStream(new File(FILE_PATH+"/course/OFAksBdN.blob"));
            while ((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
            }
            inputStream = new FileInputStream(new File(FILE_PATH+"/course/Qfy2JGx8.blob"));
            while ((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
            }
            inputStream = new FileInputStream(new File(FILE_PATH+"/course/BPfgDOO7.blob"));
            while ((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
            }
        }catch (IOException e){
            LOG.error("分片合并异常", e);
        }finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                outputStream.close();
            }catch (IOException e){
                LOG.error("IO流关闭", e);
            }
        }

        ResponseDto responseDto = new ResponseDto();
        return responseDto;

    }

}
