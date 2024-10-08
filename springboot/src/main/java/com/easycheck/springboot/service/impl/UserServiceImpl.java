package com.easycheck.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easycheck.springboot.dto.UserLoginDTO;
import com.easycheck.springboot.dto.UserRegisterDTO;
import com.easycheck.springboot.entity.EasyUser;
import com.easycheck.springboot.mapper.UserMapper;
import com.easycheck.springboot.service.UserService;
import com.easycheck.springboot.utils.Bcrypt;
import com.easycheck.springboot.utils.JwtUtil;
import com.easycheck.springboot.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    // 自动注入 redisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    // 自动注入 userMapper
    @Autowired
    private UserMapper userMapper;

    // user service 功能的实现
    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        // 判断用户名是否已存在
        EasyUser user_by_name = get_user_by_name(userRegisterDTO.getUsername());
        if (user_by_name != null)
            throw new RuntimeException("用户名已存在");
        // 判断邮箱是否已存在
        EasyUser user_by_email = get_user_by_email(userRegisterDTO.getEmail());
        if (user_by_email != null)
            throw new RuntimeException("邮箱已被使用");

        // 封装用户信息 插入
        EasyUser user = new EasyUser();
        user.setUserName(userRegisterDTO.getUsername());
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
        // 构造查询条件
        LambdaQueryWrapper<EasyUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyUser::getUserName, name);
        return userMapper.selectOne(queryWrapper);
    }

    // 通过邮箱获取用户信息
    @Override
    public EasyUser get_user_by_email(String email) {
        // 构造查询条件
        LambdaQueryWrapper<EasyUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyUser::getUserEmail, email);
        return userMapper.selectOne(queryWrapper);
    }

    // 用户登录
    @Override
    public String login(UserLoginDTO userLoginDTO, String userIpAddress) {
        // 判断用户是否存在
        EasyUser user = get_user_by_name(userLoginDTO.getUsername());
        if (user == null)
        {
            throw new RuntimeException("用户不存在");
        }

        // 密码校验
        if (Bcrypt.checkpw(userLoginDTO.getPassword(), user.getUserPassword()))
        {
            // 判断是否已登录
            String redis_token = (String) redisTemplate.opsForValue().get("easy_user_"+user.getUserId());
            if (StringUtils.hasText(redis_token)) {

                // 判断ip是否一致
                Map<String, Object> map = JwtUtil.parseToken(redis_token);
                String ip = (String)map.get("ip");
                if (!ip.equals(userIpAddress))
                {
                    log.error("用户 {} IP发生变化 {}->{}", user.getUserName(), ip, userIpAddress);
                    // 此处可向用户发送邮箱,验证码,等提示信息
                }

                redisTemplate.expire("easy_user_" + user.getUserId(), 1, TimeUnit.DAYS);
                redisTemplate.expire(redis_token, 1, TimeUnit.DAYS);

                return redis_token;

            }
            // 生成token
            Map<String,Object> map = new HashMap<>();
            map.put("username",user.getUserName());
            map.put("email",user.getUserEmail());
            map.put("id",user.getUserId());
            map.put("ip",userIpAddress);
            String token = JwtUtil.genToken(map);

            // 缓存token 1天
            redisTemplate.opsForValue().set(token,"easy_user_"+user.getUserId(),1, TimeUnit.DAYS);
            redisTemplate.opsForValue().set("easy_user_"+user.getUserId(),token,1, TimeUnit.DAYS);
            return token;
        }

        return null;
    }
}
