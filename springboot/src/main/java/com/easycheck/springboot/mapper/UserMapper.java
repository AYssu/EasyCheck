package com.easycheck.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easycheck.springboot.entity.EasyUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<EasyUser> {
}
