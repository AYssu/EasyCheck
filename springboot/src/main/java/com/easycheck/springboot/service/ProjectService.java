package com.easycheck.springboot.service;

import com.easycheck.springboot.dto.ProjectCreateDTO;
import com.easycheck.springboot.dto.ProjectListDTO;
import com.easycheck.springboot.entity.EasyProject;
import com.easycheck.springboot.vo.PageBean;
import com.easycheck.springboot.vo.ProjectListVO;

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
}
