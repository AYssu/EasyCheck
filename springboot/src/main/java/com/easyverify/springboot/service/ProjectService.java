package com.easyverify.springboot.service;

import com.easyverify.springboot.dto.*;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.vo.*;

public interface ProjectService {
    /**
     * 创建项目
     * @param projectCreateDTO 结构封装
     * @return boolean
     */
    boolean create_project(ProjectCreateDTO projectCreateDTO);

    /**
     * 根据项目名获取项目信息
     * @param projectName 程序名称
     * @return EasyProject
     */
    EasyProject get_project_by_name(String projectName);

    /**
     * 根据项目key获取项目信息
     * @param projectKey 程序key
     * @return EasyProject
     */
    EasyProject get_project_by_key(String projectKey);

    /**
     * 根据项目id获取项目信息
     * @param pid 项目id
     * @return EasyProject
     */
    EasyProject get_project_by_id_with_uid(Integer pid, Integer uid);

    /**
     * 根据项目id获取项目信息
     * @param id 项目id
     * @return EasyProject
     */
    EasyProject get_project_by_id(Integer id);

    /**
     * 获取项目列表
     * @param projectListDTO 结构封装
     * @return 分页结构
     */
    PageBean<ProjectListVO> get_project_list(ProjectListDTO projectListDTO);

    /**
     * 更新项目状态
     * @param id 项目id
     * @return boolean
     */
    boolean set_project_status(Integer id);

    /**
     * 重置项目key
     * @param pid 项目id
     * @return ProjectResetVo
     */
    ProjectResetVo reset_key_base64(Integer pid);

    /**
     * 更新项目信息
     * @param projectInfoDTO 结构封装
     * @return boolean
     */
    boolean update_normal_info(ProjectInfoDTO projectInfoDTO);

    /**
     * 获取用户绑定的项目列表
     * @param projectUserBindListDTO 结构封装
     * @return 分页结构
     */
    PageBean<ProjectUserBindListVo> get_project_user_bind_list(ProjectUserBindListDTO projectUserBindListDTO);

    /**
     * 获取项目变量
     * @param pid 项目id
     * @return Json结构
     */
    Object get_project_variable(Integer pid);

    /**
     * 设置项目变量
     * @param pid 项目id
     * @param json Json结构
     * @return boolean
     */
    boolean set_project_variable(Integer pid, Object json);

    /**
     * 获取项目更新信息
     * @param pid 项目id
     * @return ProjectUpdateVo
     */
    ProjectUpdateVo get_project_update(Integer pid);

    /**
     * 设置项目默认更新信息
     * @param pid 项目id
     * @return boolean
     */
    boolean set_project_default_update(Integer pid);

    /**
     * 更新项目更新信息
     * @param projectUpdateDTO 结构封装
     * @return boolean
     */
    boolean update_update_info(ProjectUpdateDTO projectUpdateDTO);
}
