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
import com.easycheck.springboot.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
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
        String salt = Bcrypt.gensalt(10, new SecureRandom());


        // 加密
        String encoded = Bcrypt.hashpw(userRegisterDTO.getPassword(), salt);

        log.info("encoded: {},salt: {}", encoded, salt);

        user.setUserPassword(encoded);
        user.setRegisterTime(LocalDateTime.now());

        return userMapper.insert(user) > 0;
    }

    // 获取用户信息
    // 通过id获取用户信息 因为每次查询验证的token会查询当前用户 所以使用了缓存
    @Override
    public EasyUser get_user_by_id(Integer id) {
        // 直接从数据库中查询
        EasyUser user = (EasyUser) redisTemplate.opsForValue().get("easy_user_" + id + "_struct");
        if (user != null) {
            log.info("从缓存中获取用户"+id+"的信息:{}",user);
            return user;
        }
        user = userMapper.selectById(id);
        redisTemplate.opsForValue().set("easy_user_" + id + "_struct", user, 15, TimeUnit.MINUTES);
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

    @Override
    public EasyUser get_user_by_jwt() {
        // 获取当前用户
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return get_user_by_id(id);
    }

    // 用户登录
    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO, String userIpAddress) {
        // 判断用户是否存在
        EasyUser user = get_user_by_name(userLoginDTO.getUsername());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        log.info("user {}",user);
        // 密码校验
        if (Bcrypt.checkpw(userLoginDTO.getPassword(), user.getUserPassword())) {
            // 判断是否已登录
            String redis_token = (String) redisTemplate.opsForValue().get("easy_user_" + user.getUserId());
            if (StringUtils.hasText(redis_token)) {
                try {
                    // 判断ip是否一致
                    Map<String, Object> map = JwtUtil.parseToken(redis_token);
                    String ip = (String) map.get("ip");
                    if (!ip.equals(userIpAddress)) {
                        log.error("用户 {} IP发生变化 {}->{}", user.getUserName(), ip, userIpAddress);
                        // 此处可向用户发送邮箱,验证码,等提示信息
                    }

                    //缓存token 1天 原本的想法是无感刷新token 结果刷新token后 虽然redis里面确实是有的 但是刷新token后还是无法登录
                    //当前 使用双token即可无感刷新 反正不影响实际的操作 我也想多端登录 所以暂时保留这个想法
                    //redisTemplate.expire(redis_token, 1, TimeUnit.DAYS);
                    //redisTemplate.expire("easy_user_" + user.getUserId(), 1, TimeUnit.DAYS);

                    UserLoginVO userLogin = new UserLoginVO();
                    // 拷贝用户信息
                    BeanUtils.copyProperties(user, userLogin);
                    userLogin.setToken(redis_token);
                    return userLogin;
                } catch (Exception exception) {
                    // 删除token 因为测试发现redis的缓存存在这个token 但是实际上已经过期了
                    redisTemplate.delete(redis_token);
                    redisTemplate.delete("easy_user_" + user.getUserId());

                    throw new RuntimeException("token解析失败,请重新登录!");
                }

            }
            // 生成token
            Map<String, Object> map = new HashMap<>();
            map.put("username", user.getUserName());
            map.put("email", user.getUserEmail());
            map.put("id", user.getUserId());
            map.put("ip", userIpAddress);
            String token = JwtUtil.genToken(map);

            // 缓存token 1天
            redisTemplate.opsForValue().set(token, "easy_user_" + user.getUserId(), 1, TimeUnit.DAYS);
            redisTemplate.opsForValue().set("easy_user_" + user.getUserId(), token, 1, TimeUnit.DAYS);

            // 返回用户信息
            UserLoginVO userLogin = new UserLoginVO();
            BeanUtils.copyProperties(user, userLogin);
            userLogin.setToken(redis_token);
            return userLogin;
        }

        return null;
    }
}
