package com.grady.system.controller.admin;

import com.grady.server.dto.*;
import com.grady.server.service.IUserService;
import com.grady.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    public static final String BUSINESS_NAME = "用户";

    @Resource
    public RedisTemplate redisTemplate;
    @Resource
    private IUserService iuserService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        iuserService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserDto userDto) {
        // 保存校验
        ValidatorUtil.require(userDto.getLoginName(), "登陆名");
        ValidatorUtil.length(userDto.getLoginName(), "登陆名", 1, 50);
        ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
        ValidatorUtil.require(userDto.getPassword(), "密码");

        ResponseDto responseDto = new ResponseDto();
        iuserService.save(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        iuserService.delete(id);
        return responseDto;
    }

    /**
     * 重置密码
     */
    @PostMapping("/save-password")
    public ResponseDto savePassword(@RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        iuserService.savePassword(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserDto userDto, HttpServletRequest request){
        LOG.info("用户登录开始");
        ResponseDto responseDto = new ResponseDto();
        // 根据验证码token去获取会话中的验证码，和用户输入的验证码是否一致
       // String imageCode = (String) request.getSession().getAttribute(userDto.getImageCodeToken());
        String imageCode = (String) redisTemplate.opsForValue().get(userDto.getImageCodeToken());
        LOG.info("redis中的code:{}",imageCode);
        if (StringUtils.isEmpty(imageCode)){
            responseDto.setSuccess(false);
            responseDto.setMessage("请输入验证码");
            return responseDto;
        }
        if (!imageCode.toLowerCase().equals(userDto.getImageCode().toLowerCase())){
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不正确");
            return responseDto;
        }else {
            // 验证通过后，移除验证码
            request.getSession().removeAttribute(userDto.getImageCodeToken());
        }
        LoginUserDto login = iuserService.login(userDto);
        request.getSession().setAttribute(Constants.LOGIN_USER,login);
        responseDto.setContent(login);
        return responseDto;
    }

    @GetMapping("/logout")
    public ResponseDto logout(HttpServletRequest request){
        ResponseDto responseDto = new ResponseDto();
        request.removeAttribute(Constants.LOGIN_USER);
        return responseDto;
    }
}
