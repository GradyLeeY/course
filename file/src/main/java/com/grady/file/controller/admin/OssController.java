package com.grady.file.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.grady.server.dto.FileDto;
import com.grady.server.dto.ResponseDto;
import com.grady.server.enums.FileUseEnum;
import com.grady.server.service.IFileService;
import com.grady.server.util.Base64ToMultipartFile;
import com.grady.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @Author Grady
 * @Date 2020/7/24 20:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class OssController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${accessKeySecret}")
    private String accessKeySecret;

    @Value("${endpoint}")
    private String endpoint;

    @Value("${bucket}")
    private String bucket;

    @Value("${ossDomain}")
    private String ossDomain;

    @Resource
    private IFileService fileService;

    public static final String BUSINESS_NAME = "文件上传";

    @PostMapping("/oss-append")
    public ResponseDto fileUpload(@RequestBody FileDto fileDto) throws IOException {
        LOG.info("oss文件上传开始");
        String use = fileDto.getUse();
        String key = fileDto.getKey();
        String suffix = fileDto.getSuffix();
        Integer shardIndex = fileDto.getShardIndex();
        Integer shardSize = fileDto.getShardSize();
        String shardBase64 = fileDto.getShard();
        MultipartFile shard = Base64ToMultipartFile.base64ToMultipart(shardBase64);

        //保存文件到本地
        FileUseEnum useEnum = FileUseEnum.getByCode(use);

        String dir = useEnum.name().toLowerCase();

        String path = new StringBuffer(dir)
                .append("/")
                .append(key)
                .append(".")
                .append(suffix)
                .toString(); // course\6sfSqfOwzmik4A4icMYuUe.mp4
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectMetadata meta = new ObjectMetadata();
        // 指定上传的内容类型。
        meta.setContentType("text/plain");
        // 通过AppendObjectRequest设置多个参数。
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucket, path, new ByteArrayInputStream(shard.getBytes()),meta);

        appendObjectRequest.setPosition((long) ((shardIndex - 1) * shardSize));
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);

        System.out.println(appendObjectResult.getObjectCRC());
        System.out.println(JSONObject.toJSONString(appendObjectResult));

        //        // 第二次追加。
//        // nextPosition指明下一次请求中应当提供的Position，即文件当前的长度。
//        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
//        appendObjectRequest.setInputStream(new ByteArrayInputStream(content2.getBytes()));
//        appendObjectResult = ossClient.appendObject(appendObjectRequest);
//
//        // 第三次追加。
//        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
//        appendObjectRequest.setInputStream(new ByteArrayInputStream(content3.getBytes()));
//        appendObjectResult = ossClient.appendObject(appendObjectRequest);
        ossClient.shutdown();

        LOG.info("保存文件记录开始");
        fileDto.setPath(path);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath(ossDomain + path);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    @PostMapping("/oss-simple")
    public ResponseDto fileUpload(@RequestParam MultipartFile file,String use) throws IOException {
        LOG.info("上传文件开始");
        FileUseEnum useEnum = FileUseEnum.getByCode(use);
        String key = UuidUtil.getShortUuid();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String dir = useEnum.name().toLowerCase();
        String path = dir + "/" + key + "." + suffix;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
//        String content = "Hello OSS";
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, path, new ByteArrayInputStream(file.getBytes()));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传字符串。
        ossClient.putObject(putObjectRequest);

//        LOG.info("保存文件记录开始");
//        fileDto.setPath(path);
//        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        FileDto fileDto = new FileDto();
        fileDto.setPath(ossDomain + path);
        responseDto.setContent(fileDto);

        return responseDto;
    }
}
