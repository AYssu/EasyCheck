package com.easyverify.springboot.service;

import com.easyverify.springboot.dto.OpenAPIDTO;
import com.easyverify.springboot.dto.UserProjectBindDTO;
import com.easyverify.springboot.entity.EasyLink;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.entity.OpenUser;
import com.easyverify.springboot.vo.ResponseResult;

public interface OpenAPIService {

    /**
     * 用户绑定项目
     * @param userProjectBindDTO 用户绑定项目信息
     * @return boolean
     */
    boolean user_project_bind(UserProjectBindDTO userProjectBindDTO);

    /**
     * 通过邮箱获取用户信息
     * @param email 邮箱
     * @return  用户信息封装
     */
    OpenUser get_user_by_email(String email);

    /**
     * 发送验证码邮件
     * @param email 邮箱
     * @return boolean
     */
    boolean send_register_code_mail(String email);

    /**
     * 获取项目类型
     *
     * @param pid  项目id
     * @param base 请求链接
     * @return 链接类型
     */
    EasyLink open_link_type(int pid, String base);

    /**
     * 获取项目更新信息
     *
     * @param project    项目信息
     * @param openAPIDTO 请求参数
     * @param link
     * @return 项目公告信息
     */
    ResponseResult<?> get_project_notice(EasyProject project, OpenAPIDTO openAPIDTO, EasyLink link);
}
