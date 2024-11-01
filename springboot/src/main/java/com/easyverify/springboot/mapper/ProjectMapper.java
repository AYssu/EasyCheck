package com.easyverify.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyverify.springboot.entity.EasyProject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper extends BaseMapper<EasyProject> {
}
