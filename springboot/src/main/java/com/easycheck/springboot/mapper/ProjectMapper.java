package com.easycheck.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easycheck.springboot.entity.EasyProject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper extends BaseMapper<EasyProject> {
}
