package com.easyverify.springboot.service;

import com.easyverify.springboot.dto.ProjectCreateDTO;
import com.easyverify.springboot.dto.ProjectInfoDTO;
import com.easyverify.springboot.dto.ProjectListDTO;
import com.easyverify.springboot.dto.ProjectUserBindListDTO;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.vo.PageBean;
import com.easyverify.springboot.vo.ProjectListVO;
import com.easyverify.springboot.vo.ProjectResetVo;
import com.easyverify.springboot.vo.ProjectUserBindListVo;

public interface ProjectService {
    /*
     * 创建项目
     * @param projectCreateDTO
     * @return
     */
    boolean create_project(ProjectCreateDTO projectCreateDTO);

    /*
     * 根据项目名获取项目信息
     * @param projectName
     * @return
     */
    EasyProject get_project_by_name(String projectName);

    /*
     * 根据项目key获取项目信息
     * @param projectKey
     * @return
     */
    EasyProject get_project_by_key(String projectKey);

    /*
     * 根据项目id获取项目信息
     * @param pid
     * @return
     */
    EasyProject get_project_by_id_with_uid(Integer pid, Integer uid);

    /*
     * 根据项目id获取项目信息
     * @param pid
     * @return
     */
    EasyProject get_project_by_id(Integer id);

    /*
     * 获取项目列表
     * @param projectListDTO
     * @return
     */
    PageBean<ProjectListVO> get_project_list(ProjectListDTO projectListDTO);

    /*
     * 更新项目状态
     * @param id
     * @return
     */
    boolean set_project_status(Integer id);

    /*
     * 重置项目key
     * @param pid
     * @return
     */
    ProjectResetVo reset_key_base64(Integer pid);

    /*
     * 更新项目信息
     * @param projectInfoDTO
     * @return
     */
    boolean update_normal_info(ProjectInfoDTO projectInfoDTO);

    /*
     * 获取用户绑定的项目列表
     * @param projectUserBindListDTO
     * @return
     */
    PageBean<ProjectUserBindListVo> get_project_user_bind_list(ProjectUserBindListDTO projectUserBindListDTO);
}
