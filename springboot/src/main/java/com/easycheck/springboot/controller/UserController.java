package com.easycheck.springboot.controller;

import com.easycheck.springboot.dto.UserLoginDTO;
import com.easycheck.springboot.dto.UserRegisterDTO;
import com.easycheck.springboot.service.UserService;
import com.easycheck.springboot.utils.IpUtil;
import com.easycheck.springboot.vo.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    // 注入user Service
    @Autowired
    private UserService userService;


    @PostMapping("register")
    public ResponseResult<?> user_register(@RequestBody @Validated UserRegisterDTO userRegisterDTO)
    {
        log.info("user_register_form: {}",userRegisterDTO.toString());
        if (userService.register(userRegisterDTO))
            return ResponseResult.success("注册成功!");
        return ResponseResult.fail("注册失败");
    }


    @PostMapping("login")
    public ResponseResult<?> user_login(@RequestBody @Validated UserLoginDTO userLoginDTO, HttpServletRequest request)
    {
        String ipAddr = IpUtil.getIpAddr(request);
        log.info("user_login_form: {}",userLoginDTO.toString());
        String token = userService.login(userLoginDTO, ipAddr);
        if (token != null)
            return ResponseResult.success("登录成功",token);
        return ResponseResult.fail("登录失败");
    }

}
