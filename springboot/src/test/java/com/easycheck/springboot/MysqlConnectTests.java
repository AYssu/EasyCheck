package com.easycheck.springboot;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easycheck.springboot.entity.EasyUser;
import com.easycheck.springboot.mapper.UserMapper;
import com.easycheck.springboot.vo.UserLoginVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MysqlConnectTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void read_user() {
        LambdaQueryWrapper<EasyUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyUser::getUserName, "阿夜");
        EasyUser easyUser = userMapper.selectOne(queryWrapper);

//        UserLoginVO userLoginVO = new UserLoginVO();
//        BeanUtils.copyProperties(easyUser, userLoginVO);
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(userLoginVO);
        System.out.println(easyUser);
    }
}