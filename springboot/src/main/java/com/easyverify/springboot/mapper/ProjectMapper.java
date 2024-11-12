package com.easyverify.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.vo.ProjectUserBindListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<EasyProject> {

    /*
    * 查询用户绑定的项目总数
    * @param userId
     */
    @Select("SELECT COUNT(*) \n" +
            "FROM\n" +
            "    open_user ou\n" +
            "\t\tINNER JOIN open_user_project oup  ON oup.open_user_id = ou.open_user_id \n" +
            "\t  INNER JOIN easy_project ep ON oup.project_id = ep.project_id\n" +
            "\t \n" +
            "WHERE\n" +
            "    ep.project_user = #{userId};")
    Integer select_bind_user_total(Integer userId);

    /*
    * 查询用户绑定的项目列表
    * @param size
    * @param offset
    * @param userId
     */
    @Select("SELECT\n" +
            "\tou.open_user_id,\n" +
            "\tep.project_name,\n" +
            "\tou.open_create_time,\n" +
            "\tou.open_email,\n" +
            "\tou.open_vip,\n" +
            "\tou.open_svip_time\n" +
            "FROM\n" +
            "\topen_user ou\n" +
            "\tINNER JOIN easy_project ep\n" +
            "\tINNER JOIN open_user_project oup ON ep.project_user = #{userId} \n" +
            "WHERE\n" +
            "\toup.project_id = ep.project_id \n" +
            "\tAND oup.open_user_id = ou.open_user_id \n" +
            "LIMIT #{size} OFFSET #{offset};\n")
    List<ProjectUserBindListVo> select_bind_user_list(Integer size, Integer offset, Integer userId);
}
