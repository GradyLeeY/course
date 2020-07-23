package com.grady.file.controller.admin;

import com.grady.server.dto.FileDto;
import com.grady.server.dto.ResponseDto;
import com.grady.server.enums.FileUseEnum;
import com.grady.server.service.IFileService;
import com.grady.server.util.Base64ToMultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.concurrent.TimeUnit;

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
    private IFileService fileService;

    @PostMapping("/upload")
    public ResponseDto upload(@RequestBody FileDto fileDto) throws Exception {
        LOG.info("上传文件开始");
        String use = fileDto.getUse();
        String key = fileDto.getKey();
        String suffix = fileDto.getSuffix();
        String shardBase64 = fileDto.getShard();
        MultipartFile shard = Base64ToMultipartFile.base64ToMultipart(shardBase64);

        // 保存文件到本地
        FileUseEnum useEnum = FileUseEnum.getByCode(use);

        //如果文件夹不存在则创建
        String dir = useEnum.name().toLowerCase();
        File fullDir = new File(FILE_PATH + dir);
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }

        //String path = dir + File.separator + key + "." + suffix;
        String path = new StringBuilder()
                .append(dir)
                .append(File.separator)
                .append(key).append(".")
                .append(suffix)
                .toString();

        String localPath = new StringBuilder().append(path).append(".").append(fileDto.getShardIndex()).toString();

        String fullPath = FILE_PATH + localPath;

        File dest = new File(fullPath);
        shard.transferTo(dest);
        LOG.info(dest.getAbsolutePath());

        LOG.info("保存文件记录开始");
        fileDto.setPath(path);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath(FILE_DOMAIN + path);
        responseDto.setContent(fileDto);
        if (fileDto.getShardIndex().equals(fileDto.getShardTotal())){
            this.merge(fileDto);
        }
        return responseDto;
    }


    @GetMapping("/check/{key}")
    public ResponseDto check(@PathVariable String key){
        ResponseDto responseDto = new ResponseDto();
        FileDto fileDto = fileService.findKey(key);
        if (fileDto != null){
            fileDto.setPath(FILE_DOMAIN + fileDto.getPath());
        }
        responseDto.setContent(fileDto);
        return responseDto;
    }

    public void merge(FileDto fileDto) throws Exception  {
        LOG.info("合并分片开始");
        long start = System.currentTimeMillis();
        String path = fileDto.getPath().replace(FILE_DOMAIN,"");
        Integer shardTotal = fileDto.getShardTotal();

        String mergePath = FILE_PATH + path;

        File newFile = new File(mergePath);
        FileOutputStream outputStream = new FileOutputStream(newFile,true);
        FileInputStream inputStream = null;

        byte[] bytes = new byte[1024*10*1024];
        int len;
        try {
            for (int i = 0; i < shardTotal; i++) {
                inputStream = new FileInputStream(new File(mergePath+"."+(i+1)));
                while ((len = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes,0,len);
                }
            }

        }catch (IOException e){
            LOG.error("分片合并异常", e);
        }finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                outputStream.close();
                long end = System.currentTimeMillis();
                LOG.error("IO流关闭,耗时：{}",end - start);
            }catch (IOException e){
            }
        }

        LOG.info("合并分片结束");
        System.gc();
        Thread.sleep(1000);
        //删除分片
        LOG.info("删除分片开始");
        for (int i = 0; i < shardTotal; i++) {
            String shardPath = mergePath + "." +(i+1);
            File delFile = new File(shardPath);
            try {
                boolean delete = delFile.delete();
                LOG.info("删除{},{}",shardPath,delete?"成功":"失败");
            }catch (RuntimeException e){
                LOG.info("删除分片失败");
            }
        }
    }

}
