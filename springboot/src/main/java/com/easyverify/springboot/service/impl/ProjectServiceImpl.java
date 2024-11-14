package com.easyverify.springboot.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easyverify.springboot.dto.ProjectCreateDTO;
import com.easyverify.springboot.dto.ProjectInfoDTO;
import com.easyverify.springboot.dto.ProjectListDTO;
import com.easyverify.springboot.dto.ProjectUserBindListDTO;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.entity.EasyUser;
import com.easyverify.springboot.entity.EasyVariable;
import com.easyverify.springboot.mapper.ProjectMapper;
import com.easyverify.springboot.mapper.VariableMapper;
import com.easyverify.springboot.service.UserService;
import com.easyverify.springboot.utils.Base64Util;
import com.easyverify.springboot.vo.PageBean;
import com.easyverify.springboot.vo.ProjectListVO;
import com.easyverify.springboot.service.ProjectService;
import com.easyverify.springboot.utils.StringUtil;
import com.easyverify.springboot.vo.ProjectResetVo;
import com.easyverify.springboot.vo.ProjectUserBindListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    // 注入mapper
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private VariableMapper variableMapper;

    // 自定义加密base64
    @Value("${encrypt.base64}")
    public String encrypt_base64;

    // 创建项目
    @Override
    public boolean create_project(ProjectCreateDTO projectCreateDTO) {
        // 判断项目名是否已存在
        EasyProject project = get_project_by_name(projectCreateDTO.getProjectName());
        if (project != null)
            throw new RuntimeException("该项目已存在");

        EasyUser user = userService.get_user_by_jwt();

        // 封装项目信息
        EasyProject easyProject = new EasyProject();
        easyProject.setProjectName(projectCreateDTO.getProjectName());
        easyProject.setProjectMessage(projectCreateDTO.getProjectMessage());
        easyProject.setProjectCreateTime(LocalDateTime.now());
        String project_key = StringUtil.generateRandomString(12);
        EasyProject project_key_temp = get_project_by_key(project_key);

        // 重新生成key 虽然是随机的数据 但是也存在重复的可能性
        int errors = 0;
        while (project_key_temp != null) {
            project_key = StringUtil.generateRandomString(12);
            project_key_temp = get_project_by_key(project_key);
            errors++;
            if (errors > 3)
                throw new RuntimeException("生成项目key失败");
        }
        // 生成base64 随机的 所以算法不一样
        String base64 = StringUtil.shuffleString("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        easyProject.setProjectBase64(base64);
        easyProject.setProjectKey(project_key);
        easyProject.setProjectUser(user.getUserId());
        // 插入数据库
        return projectMapper.insert(easyProject) > 0;
    }

    // 通过项目名获取项目信息
    @Override
    public EasyProject get_project_by_name(String projectName) {
        LambdaQueryWrapper<EasyProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyProject::getProjectName, projectName);
        return projectMapper.selectOne(queryWrapper);
    }

    @Override
    public EasyProject get_project_by_key(String projectKey) {
        LambdaQueryWrapper<EasyProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyProject::getProjectKey, projectKey);
        return projectMapper.selectOne(queryWrapper);
    }

    // 获取项目列表
    @Override
    public PageBean<ProjectListVO> get_project_list(ProjectListDTO projectListDTO) {
        // 封装返回值
        PageBean<ProjectListVO> pageBean = new PageBean<>();

        // 获取当前用户
        EasyUser user = userService.get_user_by_jwt();

        // 获取项目列表
        Page<EasyProject> guardPage = new Page<>(projectListDTO.getCurrentPage(), projectListDTO.getPageSize());

        LambdaQueryWrapper<EasyProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyProject::getProjectUser, user.getUserId());
        queryWrapper.like(StringUtils.hasText(projectListDTO.getProjectName()), EasyProject::getProjectName, projectListDTO.getProjectName());

        // 执行查询
        Page<EasyProject> guardPage1 = projectMapper.selectPage(guardPage, queryWrapper);
        List<EasyProject> records = guardPage1.getRecords();

        Base64Util.setBase64(encrypt_base64);
        // 转换为VO
        List<ProjectListVO> projectListVOS = records.stream().map(record -> {
            ProjectListVO projectListVO = new ProjectListVO();
            BeanUtils.copyProperties(record, projectListVO);

            // 生成绑定key 程序默认 生成不可改变
            String bind_key = Base64Util.encodeBase64Str(String.valueOf(record.getProjectId()));
            projectListVO.setBindKey(bind_key);
            return projectListVO;
        }).toList();

        // 获取所有项目名称 前端auto时需要显示全部项目名称
        LambdaQueryWrapper<EasyProject> select_all_name = new LambdaQueryWrapper<>();
        select_all_name.select(EasyProject::getProjectName);
        select_all_name.eq(EasyProject::getProjectUser, user.getUserId());
        List<String> names = projectMapper.selectObjs(select_all_name);

        log.info("names:{}", names);
        // 设置返回值
        pageBean.setTotal((int) guardPage1.getTotal());
        pageBean.setItems(projectListVOS);
        pageBean.setNames(names);
        return pageBean;
    }

    @Override
    public boolean set_project_status(Integer id) {
        EasyUser user = userService.get_user_by_jwt();

        EasyProject project = get_project_by_id_with_uid(id, user.getUserId());
        // 切换状态
        project.setProjectStatus(project.getProjectStatus() == 0 ? 1 : 0);
        return projectMapper.updateById(project) > 0;
    }

    // 重置key
    @Override
    public ProjectResetVo reset_key_base64(Integer pid) {
        // 获取当前用户
        EasyUser user = userService.get_user_by_jwt();

        // 获取项目
        EasyProject project = get_project_by_id_with_uid(pid, user.getUserId());

        String base64 = StringUtil.shuffleString("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        String project_key = StringUtil.generateRandomString(12);
        EasyProject project_key_temp = get_project_by_key(project_key);

        // 重新生成key 虽然是随机的数据 但是也存在重复的可能性
        int errors = 0;
        while (project_key_temp != null) {
            project_key = StringUtil.generateRandomString(12);
            project_key_temp = get_project_by_key(project_key);
            log.info("key create errors:" + errors);
            errors++;
            if (errors > 3)
                throw new RuntimeException("生成项目key失败");
        }
        project.setProjectBase64(base64);
        project.setProjectKey(project_key);

        boolean success = projectMapper.updateById(project) > 0;

        // 返回值 封装结构体
        ProjectResetVo projectResetVo = new ProjectResetVo();
        projectResetVo.setSuccess(success);
        projectResetVo.setProjectBase64(base64);
        projectResetVo.setProjectKey(project_key);
        return projectResetVo;
    }

    @Override
    public boolean update_normal_info(ProjectInfoDTO projectInfoDTO) {
        EasyUser user = userService.get_user_by_jwt();

        // 获取项目
        EasyProject project = get_project_by_id_with_uid(projectInfoDTO.getProjectId(), user.getUserId());

        project.setProjectName(projectInfoDTO.getProjectName());
        project.setProjectMessage(projectInfoDTO.getProjectMessage());
        return projectMapper.updateById(project) > 0;
    }

    @Override
    public PageBean<ProjectUserBindListVo> get_project_user_bind_list(ProjectUserBindListDTO projectUserBindListDTO) {
        EasyUser user = userService.get_user_by_jwt();

        Integer totalCount = projectMapper.select_bind_user_total(user.getUserId());
        log.info("totalCount:{}", totalCount);
        Integer size = projectUserBindListDTO.getPageSize();
        Integer offset = (projectUserBindListDTO.getCurrentPage() - 1) * projectUserBindListDTO.getPageSize();

        List<ProjectUserBindListVo> lists = projectMapper.select_bind_user_list(size, offset, user.getUserId());

        log.info("lists:{}", lists);
        return new PageBean<>(totalCount, lists, null);
    }

    // 获取项目变量
    @Override
    public Object get_project_variable(Integer pid) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(pid, user.getUserId());
        LambdaQueryWrapper<EasyVariable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyVariable::getProjectId, project.getProjectId());
        EasyVariable variable = variableMapper.selectOne(queryWrapper);
        if (variable == null)
            return null;
        String variableJson = variable.getVariableJson();
        Object json = JSON.parse(variableJson);
        log.info("{}=>{}", variableJson,json);
        try {
            return json;
        } catch (Exception e) {
            log.error("json parse error:{}", e.getMessage());
            throw new RuntimeException("json解析错误");
        }
    }

    @Override
    public boolean set_project_variable(Integer pid, Object json) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(pid, user.getUserId());
        LambdaQueryWrapper<EasyVariable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyVariable::getProjectId, project.getProjectId());
        EasyVariable variable = variableMapper.selectOne(queryWrapper);
        if (variable == null) {
            variable = new EasyVariable();
            variable.setProjectId(project.getProjectId());
            variable.setVariableJson(JSON.toJSONString(json));
            return variableMapper.insert(variable) > 0;
        }else {
            variable.setVariableJson(JSON.toJSONString(json));
            return variableMapper.updateById(variable) > 0;
        }
    }

    // 获取项目 根据用户ID
    @Override
    public EasyProject get_project_by_id_with_uid(Integer pid, Integer uid) {

        LambdaQueryWrapper<EasyProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyProject::getProjectId, pid);
        queryWrapper.eq(EasyProject::getProjectUser, uid);
        EasyProject project = projectMapper.selectOne(queryWrapper);
        if (project == null)
            throw new RuntimeException("项目不存在");
        return project;
    }

    @Override
    public EasyProject get_project_by_id(Integer id) {
        return projectMapper.selectById(id);
    }
}
