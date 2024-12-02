package com.easyverify.springboot.controller;

import com.easyverify.springboot.dto.UserLoginDTO;
import com.easyverify.springboot.dto.UserRegisterDTO;
import com.easyverify.springboot.service.UserService;
import com.easyverify.springboot.utils.IpUtil;
import com.easyverify.springboot.vo.ResponseResult;
import com.easyverify.springboot.vo.UserLoginVO;
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
        UserLoginVO login_success = userService.login(userLoginDTO, ipAddr);
        if (login_success != null)
            return ResponseResult.success("登录成功",login_success);
        return ResponseResult.fail("登录失败");
    }

}
