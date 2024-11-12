package com.easyverify.springboot.service;

import com.easyverify.springboot.dto.UserProjectBindDTO;
import com.easyverify.springboot.entity.OpenUser;

public interface OpenAPIService {

    /*
     * 用户绑定项目
     * @param
     * @return
     */
    boolean user_project_bind(UserProjectBindDTO userProjectBindDTO);

    /*
     * 通过邮箱获取用户信息
     * @param email
     * @return
     */
    OpenUser get_user_by_email(String email);
}
