package com.easycheck.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easycheck.springboot.dto.ProjectCreateDTO;
import com.easycheck.springboot.dto.ProjectListDTO;
import com.easycheck.springboot.entity.EasyProject;
import com.easycheck.springboot.entity.EasyUser;
import com.easycheck.springboot.mapper.ProjectMapper;
import com.easycheck.springboot.service.ProjectService;
import com.easycheck.springboot.service.UserService;
import com.easycheck.springboot.utils.StringUtil;
import com.easycheck.springboot.vo.PageBean;
import com.easycheck.springboot.vo.ProjectListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    // 注入mapper
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserService userService;
    // 创建项目
    @Override
    public boolean create_project(ProjectCreateDTO projectCreateDTO) {
        // 判断项目名是否已存在
        EasyProject project = get_project_by_name(projectCreateDTO.getProjectName());
        if (project != null)
            throw new RuntimeException("该项目已存在");

        EasyUser user = userService.get_user_by_jwt();
        if (user == null)
            throw new RuntimeException("用户不存在");
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
        if (user == null)
            throw new RuntimeException("用户不存在");

        // 获取项目列表
        Page<EasyProject> guardPage = new Page<>(projectListDTO.getCurrentPage(), projectListDTO.getPageSize());

        LambdaQueryWrapper<EasyProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyProject::getProjectUser,user.getUserId());
        queryWrapper.like(StringUtils.hasText(projectListDTO.getProjectName()),EasyProject::getProjectName,projectListDTO.getProjectName());

        // 执行查询
        Page<EasyProject> guardPage1 = projectMapper.selectPage(guardPage, queryWrapper);
        List<EasyProject> records = guardPage1.getRecords();

        // 转换为VO
        List<ProjectListVO> projectListVOS = records.stream().map(record -> {
            ProjectListVO projectListVO = new ProjectListVO();
            BeanUtils.copyProperties(record,projectListVO);
            return projectListVO;
        }).toList();

        // 设置返回值
        pageBean.setTotal((int) guardPage1.getTotal());
        pageBean.setItems(projectListVOS);

        return pageBean;
    }
}
