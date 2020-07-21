package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.File;
import com.grady.server.domain.FileExample;
import com.grady.server.dto.FileDto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.FileMapper;
import com.grady.server.service.IFileService;
import com.grady.server.util.CopyUtil;
import com.grady.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
        import java.util.Date;
/**
 * @Author Grady
 * @Date 2020/5/24 14:05
 * @Version 1.0
 */
@Service
public class FileServiceImpl implements IFileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        FileExample fileExample = new FileExample();
        List<File> fileList = fileMapper.selectByExample(fileExample);
        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        pageDto.setTotal(pageInfo.getTotal());

        List<FileDto> fileDtoList = CopyUtil.copyList(fileList,FileDto.class);
        pageDto.setList(fileDtoList);
    }

    @Override
    public void save(FileDto fileDto){
        File file = CopyUtil.copy(fileDto,File.class);
        File fileDb = selectByKey(fileDto.getKey());
        if(fileDb == null){
            insert(file);
        }else {
            fileDb.setShardIndex(fileDto.getShardIndex());
            update(fileDb);
        }
    }

    @Override
    public void delete(String id){
        fileMapper.deleteByPrimaryKey(id);
    }

    @Override
    public File selectByKey(String key){
        FileExample fileExample = new FileExample();
        fileExample.createCriteria().andKeyEqualTo(key);
        List<File> fileList = fileMapper.selectByExample(fileExample);
        if (CollectionUtils.isEmpty(fileList)){
            return null;
        }else {
            return fileList.get(0);
        }
    }


    private void update(File file) {
            file.setUpdatedAt(new Date());
        fileMapper.updateByPrimaryKey(file);
    }

    private void insert(File file) {
        Date now = new Date();
            file.setCreatedAt(now);
            file.setUpdatedAt(now);
        file.setId(UuidUtil.getShortUuid());
        fileMapper.insert(file);
    }

}