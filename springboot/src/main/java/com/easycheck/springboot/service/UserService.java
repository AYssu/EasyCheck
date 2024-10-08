package com.easycheck.springboot.service;

import com.easycheck.springboot.dto.UserLoginDTO;
import com.easycheck.springboot.dto.UserRegisterDTO;
import com.easycheck.springboot.entity.EasyUser;

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
    * 用户登录
    * @param userLoginDTO
    * @param userIpAddress
    * @return
     */
    String login(UserLoginDTO userLoginDTO, String userIpAddress);
}
