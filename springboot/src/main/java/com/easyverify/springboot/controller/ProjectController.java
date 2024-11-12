package com.easyverify.springboot.controller;

import com.easyverify.springboot.dto.ProjectCreateDTO;
import com.easyverify.springboot.dto.ProjectInfoDTO;
import com.easyverify.springboot.dto.ProjectListDTO;
import com.easyverify.springboot.dto.ProjectUserBindListDTO;
import com.easyverify.springboot.vo.PageBean;
import com.easyverify.springboot.vo.ProjectResetVo;
import com.easyverify.springboot.vo.ProjectUserBindListVo;
import com.easyverify.springboot.vo.ResponseResult;
import com.easyverify.springboot.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("project_set_status")
    public ResponseResult<?> project_set_status(@RequestParam Integer id){
        log.info("project_set_status_form: {}", id);
        boolean set_success = projectService.set_project_status(id);
        if (!set_success)
            return ResponseResult.fail("设置失败");
        return ResponseResult.success("设置成功");
    }

    @GetMapping("project_reset_key")
    public ResponseResult<?> project_reset_key(@RequestParam Integer pid){
        log.info("project_reset_key_form project id: {}", pid);
        if (pid == null|| pid <= 0)
            return ResponseResult.fail("参数错误");

        // 调用service层
        ProjectResetVo reset_success = projectService.reset_key_base64(pid);
        if (!reset_success.isSuccess())
            return ResponseResult.fail("重置失败");
        return ResponseResult.success("重置成功", reset_success);
    }

    @PostMapping("project_update_info")
    public ResponseResult<?> project_update_info(@RequestBody @Validated ProjectInfoDTO projectInfoDTO){
        log.info("project_update_info_form: {}", projectInfoDTO);
        // 调用service层
        boolean update_success = projectService.update_normal_info(projectInfoDTO);
        if (!update_success)
            return ResponseResult.fail("更新失败");
        return ResponseResult.success("更新成功");
    }

    @PostMapping("project_bind_list")
    public ResponseResult<?> project_bind_list(@RequestBody @Validated ProjectUserBindListDTO projectUserBindListDTO){

        log.info("project_bind_list{}", projectUserBindListDTO);
        PageBean<ProjectUserBindListVo> projectUserBindListVoPageBean = projectService.get_project_user_bind_list(projectUserBindListDTO);
        return ResponseResult.success("查询成功", projectUserBindListVoPageBean);
    }
}
