package com.easyverify.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyverify.springboot.entity.EasyProjectUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateMapper extends BaseMapper<EasyProjectUpdate> {
}