package com.easycheck.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easycheck.springboot.dto.UserRegisterDTO;
import com.easycheck.springboot.entity.EasyUser;
import com.easycheck.springboot.mapper.UserMapper;
import com.easycheck.springboot.service.UserService;
import com.easycheck.springboot.utils.Bcrypt;
import com.easycheck.springboot.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    // 自动注入 userMapper
    @Autowired
    private UserMapper userMapper;

    // user service 功能的实现
    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        // 判断用户名是否已存在
        EasyUser user_by_name = get_user_by_name(userRegisterDTO.getUser());
        if (user_by_name != null)
            throw new RuntimeException("用户名已存在");
        // 判断邮箱是否已存在
        EasyUser user_by_email = get_user_by_email(userRegisterDTO.getEmail());
        if (user_by_email != null)
            throw new RuntimeException("邮箱已被使用");

        // 封装用户信息 插入
        EasyUser user = new EasyUser();
        user.setUserName(userRegisterDTO.getUser());
        user.setUserEmail(userRegisterDTO.getEmail());

        // 密码加密
        String salt = Bcrypt.gensalt(10, new SecureRandom());;

        // 加密
        String encoded = Bcrypt.hashpw(userRegisterDTO.getPassword(), salt);

        log.info("encoded: {},salt: {}", encoded, salt);

        user.setUserPassword(encoded);

        return userMapper.insert(user) > 0;
    }

    // 获取用户信息
    @Override
    public EasyUser get_user_by_id(Integer id) {
        return userMapper.selectById(id);
    }

    // 通过用户名获取用户信息
    @Override
    public EasyUser get_user_by_name(String name) {
        LambdaQueryWrapper<EasyUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyUser::getUserName, name);
        return userMapper.selectOne(queryWrapper);
    }

    // 通过邮箱获取用户信息
    @Override
    public EasyUser get_user_by_email(String email) {
        LambdaQueryWrapper<EasyUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyUser::getUserEmail, email);
        return userMapper.selectOne(queryWrapper);
    }
}
