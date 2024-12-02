package com.easyverify.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyverify.springboot.entity.OpenUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OpenUserMapper extends BaseMapper<OpenUser> {

    /**
     * 插入用户和项目的关联关系
     * @param openUserId 用户ID
     * @param projectId 项目ID
     * @return 影响的行数
     */
    @Insert("INSERT INTO open_user_project(open_user_id, project_id) VALUES (#{openUserId}, #{projectId})")
    int insert_user_project(@Param("openUserId") Integer openUserId, @Param("projectId") Integer projectId);
}
