package com.easyverify.springboot.controller;

import com.easyverify.springboot.dto.UserProjectBindDTO;
import com.easyverify.springboot.service.OpenAPIService;
import com.easyverify.springboot.service.UserService;
import com.easyverify.springboot.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("open")
@Slf4j
public class OpenAPIController {

    @Autowired
    private OpenAPIService openAPIService;

    @PostMapping("user_project_bind")
    public ResponseResult<?> user_project_bind(@RequestBody @Validated UserProjectBindDTO userProjectBindDTO)
    {
        log.info("user_project_bind form: {}",userProjectBindDTO);

        boolean success = openAPIService.user_project_bind(userProjectBindDTO);
        if (success)
            return ResponseResult.success("注册成功");
        return ResponseResult.fail("注册失败");
    }

}
