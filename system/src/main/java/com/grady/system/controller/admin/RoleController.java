package com.grady.system.controller.admin;

import com.grady.server.dto.RoleDto;
import com.grady.server.dto.PageDto;
import com.grady.server.dto.ResponseDto;
import com.grady.server.service.IRoleService;
import com.grady.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);
    public static final String BUSINESS_NAME = "角色";

    @Resource
    private IRoleService iroleService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        iroleService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody RoleDto roleDto) {
        // 保存校验
        ValidatorUtil.require(roleDto.getName(), "角色");
        ValidatorUtil.length(roleDto.getName(), "角色", 1, 50);
        ValidatorUtil.require(roleDto.getDesc(), "描述");
        ValidatorUtil.length(roleDto.getDesc(), "描述", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        iroleService.save(roleDto);
        responseDto.setContent(roleDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        iroleService.delete(id);
        return responseDto;
    }

    @PostMapping("/save-resource")
    public ResponseDto saveResource(@RequestBody RoleDto roleDto){
        ResponseDto<RoleDto> responseDto = new ResponseDto<>();
        iroleService.saveResource(roleDto);
        responseDto.setContent(roleDto);
        return responseDto;
    }

    @GetMapping("/list-resource/{roleId}")
    public ResponseDto listResource(@PathVariable String roleId){
        ResponseDto responseDto = new ResponseDto();

        List<String> resourceIds = iroleService.listResource(roleId);
        responseDto.setContent(resourceIds);
        return responseDto;
    }

    @PostMapping("/save-user")
    public ResponseDto saveUser(@RequestBody RoleDto roleDto){
        ResponseDto<RoleDto> responseDto = new ResponseDto<>();
        iroleService.saveUser(roleDto);
        responseDto.setContent(roleDto);
        return responseDto;
    }

    @GetMapping("/list-user/{roleId}")
    public ResponseDto listUser(@PathVariable String roleId){
        ResponseDto responseDto = new ResponseDto();
        List<String> listUser = iroleService.listUser(roleId);
        responseDto.setContent(listUser);
        return responseDto;
    }
}
