package com.easyverify.springboot.controller;

import com.easyverify.springboot.dto.OpenAPIDTO;
import com.easyverify.springboot.dto.UserProjectBindDTO;
import com.easyverify.springboot.service.OpenAPIService;
import com.easyverify.springboot.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@RestController
public class OpenAPIController {

    @Autowired
    private OpenAPIService openAPIService;


    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    /**
     * 验证邮箱格式
     * @param email 邮箱地址
     * @return boolean
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.length() < 5) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 发送注册验证码
     * @param email 邮箱地址
     * @return 返回数据
     */
    @GetMapping("/open/send_register_code")
    public ResponseResult<?> send_code(@RequestParam String email)
    {
        if (!isValidEmail(email))
        {
            return ResponseResult.fail("邮箱格式不正确");
        }
        log.info("send_code email: {}",email);
        boolean success = openAPIService.send_register_code_mail(email);
        if (!success)
            return ResponseResult.fail("发送失败");
        return ResponseResult.success("发送成功");
    }

    /**
     * 用户绑定项目
     * @param userProjectBindDTO 绑定信息
     * @return 返回数据
     */
    @PostMapping("/open/user_project_bind")
    public ResponseResult<?> user_project_bind(@RequestBody @Validated UserProjectBindDTO userProjectBindDTO)
    {
        log.info("user_project_bind form: {}",userProjectBindDTO);

        boolean success = openAPIService.user_project_bind(userProjectBindDTO);
        if (success)
            return ResponseResult.success("注册成功");
        return ResponseResult.fail("注册失败");
    }

    @PostMapping("/{base}")
    public ResponseResult<?> open_api(@PathVariable String base,@RequestBody @Validated OpenAPIDTO openAPIDTO)
    {

        return ResponseResult.fail("");
    }

}
