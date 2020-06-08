package com.grady.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grady.server.domain.${Domain};
import com.grady.server.domain.${Domain}Example;
import com.grady.server.dto.${Domain}Dto;
import com.grady.server.dto.PageDto;
import com.grady.server.mapper.${Domain}Mapper;
import com.grady.server.service.I${Domain}Service;
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
public class ${Domain}ServiceImpl implements I${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    @Override
    public void list(PageDto pageDto){
        //插件分页语句规则，调用startpage方法后执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);

        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());
        /*for (int i = 0, l = ${domain}List.size(); i < l; i++) {
            ${Domain} ${domain} = ${domain}List.get(i);
            ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
            BeanUtils.copyProperties(${domain}, ${domain}Dto);
            ${domain}DtoList.add(${domain}Dto);
        }*/
        List<${Domain}Dto> ${domain}DtoList = CopyUtil.copyList(${domain}List,${Domain}Dto.class);
        pageDto.setList(${domain}DtoList);
    }

    @Override
    public void save(${Domain}Dto ${domain}Dto){
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto,${Domain}.class);
        if (StringUtils.isEmpty(${domain}Dto.getId())){
            this.insert(${domain});
        }else {
            this.update(${domain});
        }
    }

    @Override
    public void delete(String id){
        ${domain}Mapper.deleteByPrimaryKey(id);
    }

    private void update(${Domain} ${domain}) {
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }

    private void insert(${Domain} ${domain}) {
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }
}
