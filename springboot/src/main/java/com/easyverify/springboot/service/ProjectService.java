package com.easyverify.springboot.service;

import com.easyverify.springboot.dto.ProjectCreateDTO;
import com.easyverify.springboot.dto.ProjectListDTO;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.vo.PageBean;
import com.easyverify.springboot.vo.ProjectListVO;

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
}
