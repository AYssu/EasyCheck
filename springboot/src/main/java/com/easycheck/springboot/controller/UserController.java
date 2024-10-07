package com.easycheck.springboot.controller;

import com.easycheck.springboot.dto.UserRegisterDTO;
import com.easycheck.springboot.service.UserService;
import com.easycheck.springboot.vo.ResponseResult;
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
        boolean register_success = userService.register(userRegisterDTO);
        if (register_success)
            return ResponseResult.success("用户: "+userRegisterDTO.getUser()+" 注册成功!");
        return ResponseResult.fail("注册失败");
    }
}
