package com.easyverify.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyverify.springboot.entity.EasyVariable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VariableMapper extends BaseMapper<EasyVariable> {
}
