package com.easyverify.springboot.controller;

import com.easyverify.springboot.dto.*;
import com.easyverify.springboot.vo.*;
import com.easyverify.springboot.service.ProjectService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("project_get_variable")
    public ResponseResult<?> project_get_variable(@RequestParam Integer pid){
        log.info("project_get_variable_form: {}", pid);
        return ResponseResult.success("查询成功", projectService.get_project_variable(pid));
    }

    @PostMapping("project_set_variable")
    public ResponseResult<?> project_set_variable(@RequestBody ProjectSetVariableDTO projectSetVariableDTO){
        log.info("project_set_variable_form: {}", projectSetVariableDTO);
        boolean is_success = projectService.set_project_variable(projectSetVariableDTO.getPid(), projectSetVariableDTO.getJson());
        if (!is_success)
            return ResponseResult.fail("设置失败");
        return ResponseResult.success("设置成功");
    }

    @GetMapping("project_set_default_update")
    public ResponseResult<?> project_set_default_update(@RequestParam Integer pid){
        log.info("project_set_default_update: {}", pid);
        boolean is_success = projectService.set_project_default_update(pid);
        if (!is_success)
            return ResponseResult.fail("更新初始化成功");
        return ResponseResult.success("设置成功");
    }

    @GetMapping("project_get_update")
    public ResponseResult<?> project_get_update(@RequestParam Integer pid){
        log.info("project_get_update_form: {}", pid);
        return ResponseResult.success("查询成功", projectService.get_project_update(pid));
    }

    @PostMapping("project_update_update")
    public ResponseResult<?> project_update_update(@RequestBody ProjectUpdateDTO projectUpdateDTO){
        log.info("project_update_update_form: {}", projectUpdateDTO);
        boolean is_success = projectService.update_update_info(projectUpdateDTO);
        if (!is_success)
            return ResponseResult.fail("更新失败");
        return ResponseResult.success("更新成功");
    }

    @PostMapping("project_update_notice")
    public ResponseResult<?> project_update_notice(@RequestBody @Validated ProjectUpdateNoticeDTO projectUpdateNoticeDTO)
    {
        log.info("project_update_notice_form: {}", projectUpdateNoticeDTO);
        boolean is_success = projectService.update_update_notice_info(projectUpdateNoticeDTO.getPid(), projectUpdateNoticeDTO.getNotice());
        if (!is_success)
        {
            return ResponseResult.fail("更新失败");
        }
        return ResponseResult.success("更新成功!");
    }

    @GetMapping("project_get_link")
    public ResponseResult<?> project_get_link(@RequestParam Integer pid){
        log.info("project_get_link_form: {}", pid);
        List<LinksVo> linksVo = projectService.get_project_links(pid);
        return ResponseResult.success("查询成功", linksVo);
    }

    @PostMapping("project_add_link")
    public ResponseResult<?> project_add_link(@RequestBody @Validated ProjectLinkDTO projectLinkDTO){
        log.info("project_add_link_form: {}", projectLinkDTO);
        String is_success = projectService.add_project_link(projectLinkDTO);
        return ResponseResult.success("添加成功", is_success);
    }
    @PostMapping("project_update_link")
    public ResponseResult<?> project_update_link(@RequestBody ProjectLinkDTO projectLinkDTO){
        log.info("project_update_link_form: {}", projectLinkDTO);
        boolean is_success = projectService.update_project_link(projectLinkDTO);
        if (!is_success)
            return ResponseResult.fail("更新失败");
        return ResponseResult.success("更新成功");
    }

    @GetMapping("project_link_delete")
    public ResponseResult<?> project_link_delete(@RequestParam Integer aid)
    {
        log.info("project_link_delete_form: {}", aid);
        boolean is_success = projectService.delete_project_link(aid);
        if (is_success)
            return ResponseResult.success("删除成功");
        return ResponseResult.success("删除失败");
    }
}
