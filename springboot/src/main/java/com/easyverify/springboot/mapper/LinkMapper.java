package com.easyverify.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyverify.springboot.entity.EasyLink;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LinkMapper extends BaseMapper<EasyLink> {
}
