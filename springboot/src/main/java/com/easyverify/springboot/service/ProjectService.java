package com.easyverify.springboot.service;

import com.easyverify.springboot.dto.*;
import com.easyverify.springboot.entity.EasyLink;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.entity.EasyProjectUpdate;
import com.easyverify.springboot.vo.*;

import java.util.List;

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
     * 根据项目链接类型获取项目链接名称
     * @param type 项目链接类型
     * @return 项目链接名称
     */
    String get_link_name(Integer type);

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
     * 获取项目变量从缓存
     * @param pid 项目id
     * @return Json结构
     */
    String get_project_variable_pid_redis(Integer pid);
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
     * 根据项目更新id获取项目更新信息
     * @param id 项目更新id
     * @return EasyProjectUpdate
     */
    EasyProjectUpdate get_project_update_by_id(Integer id);
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

    /**
     * 更新项目通知信息
     * @param pid 程序ID
     * @param notice 通知内容
     * @return boolean
     */
    boolean update_project_notice_info(Integer pid, String notice);

    /**
     * 获取项目链接
     *
     * @param pid 项目id
     * @return LinksVo
     */
    List<LinksVo> get_project_links(Integer pid);

    /**
     * 获取项目链接从缓存
     * @param pid 项目id
     * @return LinksVo
     */
    List<EasyLink> get_project_links_redis(Integer pid);
    /**
     * 添加项目链接
     *
     * @param projectLinkDTO 结构封装
     * @return String
     */
    String add_project_link(ProjectLinkDTO projectLinkDTO);

    /**
     * 绑定项目
     * @param key 项目key
     * @return EasyLink
     */
    EasyLink get_link_by_key(String key);

    /**
     * 更新项目链接
     * @param projectLinkDTO 结构封装
     * @return boolean
     */
    boolean update_project_link(ProjectLinkDTO projectLinkDTO);

    /**
     * 删除项目链接
     * @param id 项目链接id
     * @return boolean
     */
    boolean delete_project_link(Integer id);

    /**
     * 添加项目卡密
     *
     * @param projectAddCardDTO 结构封装
     * @return boolean
     */
    ReturnVo add_project_card(ProjectAddCardDTO projectAddCardDTO);
}
