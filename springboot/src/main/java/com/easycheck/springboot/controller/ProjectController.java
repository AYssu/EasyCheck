package com.easycheck.springboot.controller;

import com.easycheck.springboot.dto.ProjectCreateDTO;
import com.easycheck.springboot.dto.ProjectListDTO;
import com.easycheck.springboot.service.ProjectService;
import com.easycheck.springboot.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
@Slf4j
public class ProjectController {

    // 自动装配
    @Autowired
    private ProjectService projectService;
    @PostMapping("project_create")
    public ResponseResult<?> project_create(@RequestBody @Validated ProjectCreateDTO projectCreateDTO){
        log.info("project_create_form: {}", projectCreateDTO);
        // 调用service层 创建项目
        boolean create_success = projectService.create_project(projectCreateDTO);
        if (!create_success) {
            return ResponseResult.fail("创建失败");
        }
        return ResponseResult.success("创建成功");
    }

    @PostMapping("project_list")
    public ResponseResult<?> project_list(@RequestBody @Validated ProjectListDTO projectListDTO){
        log.info("project_list_form: {}", projectListDTO);
        // 调用service层
        return ResponseResult.success("查询成功", projectService.get_project_list(projectListDTO));
    }
}
