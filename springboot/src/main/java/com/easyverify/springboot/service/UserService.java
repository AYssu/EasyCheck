package com.easyverify.springboot.service;

import com.easyverify.springboot.dto.UserLoginDTO;
import com.easyverify.springboot.dto.UserProjectBindDTO;
import com.easyverify.springboot.dto.UserRegisterDTO;
import com.easyverify.springboot.entity.EasyUser;
import com.easyverify.springboot.vo.UserLoginVO;

public interface UserService {

    /*
    * 用户注册
    * @param userRegisterDTO
    * @return
     */
    boolean register(UserRegisterDTO userRegisterDTO);


    /*
    * 根据id获取用户信息
    * @param id
    * @return
     */
    EasyUser get_user_by_id(Integer id);

    /*
    * 根据用户名获取用户信息
    * @param name
    * @return
     */
    EasyUser get_user_by_name(String name);

    /*
    * 根据邮箱获取用户信息
    * @param email
    * @return
     */
    EasyUser get_user_by_email(String email);

    /*
    * 获取当前登录用户信息
    * @return
     */
    EasyUser get_user_by_jwt();


    /*
    * 用户登录
    * @param userLoginDTO
    * @param userIpAddress
    * @return
     */
    UserLoginVO login(UserLoginDTO userLoginDTO, String userIpAddress);

}
