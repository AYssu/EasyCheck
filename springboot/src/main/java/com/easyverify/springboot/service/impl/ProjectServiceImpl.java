package com.easyverify.springboot.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easyverify.springboot.dto.*;
import com.easyverify.springboot.entity.*;
import com.easyverify.springboot.mapper.*;
import com.easyverify.springboot.service.CardService;
import com.easyverify.springboot.service.UserService;
import com.easyverify.springboot.utils.Sutils;
import com.easyverify.springboot.vo.*;
import com.easyverify.springboot.service.ProjectService;
import com.easyverify.springboot.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    @Autowired
    private UpdateMapper updateMapper;


    @Autowired
    private CardService cardService;

    // 自定义加密base64
    @Value("${encrypt.base64}")
    public String encrypt_base64;

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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

        Sutils.set_base64(encrypt_base64);
        // 转换为VO
        List<ProjectListVO> projectListVOS = records.stream().map(record -> {
            ProjectListVO projectListVO = new ProjectListVO();
            BeanUtils.copyProperties(record, projectListVO);

            // 生成绑定key 程序默认 生成不可改变
            String bind_key = Sutils.base64_encode(String.valueOf(record.getProjectId()));
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
        redisTemplate.delete("open_project_" + id);
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
        redisTemplate.delete("open_project_" + pid);
        // 返回值 封装结构体
        ProjectResetVo projectResetVo = new ProjectResetVo();
        projectResetVo.setSuccess(success);
        projectResetVo.setProjectBase64(base64);
        projectResetVo.setProjectKey(project_key);
        return projectResetVo;
    }

    /**
     * 验证公钥格式是否正确
     *
     * @param publicKeyStr 公钥字符串
     * @return 验证结果
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isValidPublicKey(String publicKeyStr) {
        try {
            RSA rsa = new RSA(null, publicKeyStr);
            // 尝试获取公钥对象，如果失败则格式不正确
            rsa.getPublicKey();
            return true;
        } catch (Exception e) {
            // 任何异常都表示公钥格式不正确
            return false;
        }
    }

    /**
     * 验证私钥格式是否正确
     *
     * @param privateKeyStr 私钥字符串
     * @return 验证结果
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")

    public static boolean isValidPrivateKey(String privateKeyStr) {
        try {
            RSA rsa = new RSA(privateKeyStr, null);
            // 尝试获取私钥对象，如果失败则格式不正确
            rsa.getPrivateKey();
            return true;
        } catch (Exception e) {
            // 任何异常都表示私钥格式不正确
            return false;
        }
    }

    public static boolean isValidKeys(String publicKeyStr, String privateKeyStr) {
        try {
            RSA rsa = new RSA(privateKeyStr, publicKeyStr);
            byte[] encrypt = rsa.encrypt(StrUtil.bytes("test测试", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
            byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
            if (!StrUtil.equals(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8), "test测试"))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update_normal_info(ProjectInfoDTO projectInfoDTO) {
        EasyUser user = userService.get_user_by_jwt();

        // 获取项目
        EasyProject project = get_project_by_id_with_uid(projectInfoDTO.getProjectId(), user.getUserId());

        EasyProject project_temp = get_project_by_name(projectInfoDTO.getProjectName());
        if (project_temp != null && !project_temp.getProjectId().equals(project.getProjectId()))
            throw new RuntimeException("项目名已存在");
        project.setProjectName(projectInfoDTO.getProjectName());

        project.setProjectMessage(projectInfoDTO.getProjectMessage());
        project.setReturnUpdate(projectInfoDTO.getReturnUpdate());
        project.setReturnVerify(projectInfoDTO.getReturnVerify());
        project.setProjectEncryption(projectInfoDTO.getProjectEncryption());

        String public_key = projectInfoDTO.getProjectRsaPublic();
        String private_key = projectInfoDTO.getProjectRsaPrivate();
        if (projectInfoDTO.getProjectEncryption() == 2) {
            // 校验公钥私钥是否正确
            if (private_key.contains("-----BEGIN PRIVATE KEY-----") && private_key.contains("-----END PRIVATE KEY-----")) {
                if (public_key.contains("-----BEGIN PUBLIC KEY-----") && public_key.contains("-----END PUBLIC KEY-----")) {
                    String public_key_base64 = public_key.replace("-----BEGIN PUBLIC KEY-----", "")
                            .replace("-----END PUBLIC KEY-----", "")
                            .replaceAll("\\s+", "");

                    String private_key_base64 = private_key.replace("-----BEGIN PRIVATE KEY-----", "")
                            .replace("-----END PRIVATE KEY-----", "")
                            .replaceAll("\\s+", "");

                    if (!isValidPublicKey(public_key_base64)) {
                        throw new RuntimeException("公钥格式错误");
                    }
                    if (!isValidPrivateKey(private_key_base64)) {
                        throw new RuntimeException("私钥格式错误");
                    }

                    if (!isValidKeys(public_key_base64, private_key_base64))
                        throw new RuntimeException("公钥私钥不匹配");
                } else {
                    throw new RuntimeException("公钥格式错误");
                }
            } else {
                throw new RuntimeException("私钥格式错误");
            }
        }

        project.setProjectRsaPrivate(private_key);
        project.setProjectRsaPublic(public_key);
        redisTemplate.delete("open_project_" + projectInfoDTO.getProjectId());
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

        Object json = JSON.parse(get_project_variable_pid_redis(project.getProjectId()));
        try {
            return json;
        } catch (Exception e) {
            log.error("json parse error:{}", e.getMessage());
            throw new RuntimeException("json解析错误");
        }
    }

    @Override
    public String  get_project_variable_pid_redis(Integer pid) {
        String variable_redis = (String)redisTemplate.opsForValue().get("easy_variable_"+pid);
        if (variable_redis != null)
            return variable_redis;
        LambdaQueryWrapper<EasyVariable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyVariable::getProjectId, pid);
        EasyVariable variable = variableMapper.selectOne(queryWrapper);
        if (variable == null)
            return null;
        String variableJson = variable.getVariableJson();
        redisTemplate.opsForValue().set("easy_variable_"+pid, variableJson, 60, TimeUnit.MINUTES);
        return variableJson;
    }
    @Override
    public boolean set_project_variable(Integer pid, Object json) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(pid, user.getUserId());
        LambdaQueryWrapper<EasyVariable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyVariable::getProjectId, project.getProjectId());
        EasyVariable variable = variableMapper.selectOne(queryWrapper);
        redisTemplate.delete("easy_variable_" + pid);
        if (variable == null) {
            variable = new EasyVariable();
            variable.setProjectId(project.getProjectId());
            variable.setVariableJson(JSON.toJSONString(json));
            return variableMapper.insert(variable) > 0;
        } else {
            variable.setVariableJson(JSON.toJSONString(json));
            return variableMapper.updateById(variable) > 0;
        }
    }


    // 获取项目更新
    @Override
    public ProjectUpdateVo get_project_update(Integer pid) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(pid, user.getUserId());

        EasyProjectUpdate projectUpdate = get_project_update_by_id(project.getProjectId());
        if (projectUpdate == null) {
            return null;
        }
        // 封装返回值
        ProjectUpdateVo projectUpdateVo = new ProjectUpdateVo();
        BeanUtils.copyProperties(projectUpdate, projectUpdateVo);
        return projectUpdateVo;
    }

    @Override
    public EasyProjectUpdate get_project_update_by_id(Integer id) {
        EasyProjectUpdate update = (EasyProjectUpdate) redisTemplate.opsForValue().get("easy_project_update_" + id);
        if (update != null)
            return update;
        LambdaQueryWrapper<EasyProjectUpdate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyProjectUpdate::getProjectId, id);
        EasyProjectUpdate projectUpdate = updateMapper.selectOne(queryWrapper);
        redisTemplate.opsForValue().set("easy_project_update_" + id, projectUpdate, 10, TimeUnit.MINUTES);
        return projectUpdate;
    }

    @Override
    public boolean set_project_default_update(Integer pid) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(pid, user.getUserId());


        EasyProjectUpdate projectUpdate = get_project_update_by_id(project.getProjectId());
        if (projectUpdate != null)
            throw new RuntimeException("项目已存在更新");
        // 插入一条记录
        EasyProjectUpdate new_projectUpdate = new EasyProjectUpdate();
        new_projectUpdate.setProjectId(project.getProjectId());
        return updateMapper.insert(new_projectUpdate) > 0;
    }

    @Override
    public boolean update_update_info(ProjectUpdateDTO projectUpdateDTO) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(projectUpdateDTO.getPid(), user.getUserId());
        EasyProjectUpdate projectUpdate = get_project_update_by_id(project.getProjectId());
        if (projectUpdate == null) {
            throw new RuntimeException("项目不存在更新功能");
        }
        // 更新
        projectUpdate.setUpdateMessage(projectUpdateDTO.getUpdateMessage());
        projectUpdate.setUpdateUrl(projectUpdateDTO.getUpdateUrl());
        projectUpdate.setUpdateVersion(projectUpdateDTO.getUpdateVersion());
        projectUpdate.setMustUpdate(projectUpdateDTO.getMustUpdate());
        redisTemplate.delete("easy_project_update_" + project.getProjectId());
        return updateMapper.updateById(projectUpdate) > 0;
    }

    // 更新通知
    @Override
    public boolean update_project_notice_info(Integer pid, String notice) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(pid, user.getUserId());
        project.setProjectNotice(notice);
        redisTemplate.delete("open_project_" + pid);
        return projectMapper.updateById(project) > 0;
    }

    @Override
    public List<LinksVo> get_project_links(Integer pid) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(pid, user.getUserId());

        List<EasyLink> links = get_project_links_redis(project.getProjectId());
        if (links == null || links.isEmpty())
            return null;
        return links.stream()
                .map(link -> {
                    LinksVo linksVo = new LinksVo();
                    BeanUtils.copyProperties(link, linksVo);
                    return linksVo;
                })
                .collect(Collectors.toList());
    }

    // 获取项目链接列表 使用redis缓存 减少数据库查询
    @Override
    public List<EasyLink> get_project_links_redis(Integer pid) {
        Object object = redisTemplate.opsForValue().get("project_links_" + pid);
        if (object instanceof List<?> list) {
            if (!list.isEmpty() && list.get(0) instanceof EasyLink) {
                log.info("redis get project links temp {}", pid);
                return (List<EasyLink>) list;
            }
        }
        LambdaQueryWrapper<EasyLink> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyLink::getProjectId, pid);
        List<EasyLink> easyLinks = linkMapper.selectList(queryWrapper);
        if (easyLinks.isEmpty()) {
            return null;
        }
        redisTemplate.opsForValue().set("project_links_" + pid, easyLinks, 30, TimeUnit.MINUTES);
        return easyLinks;
    }

    @Override
    public String add_project_link(ProjectLinkDTO projectLinkDTO) {
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(projectLinkDTO.getProjectId(), user.getUserId());

        List<EasyLink> links = get_project_links_redis(project.getProjectId());

        Integer[] types = projectLinkDTO.getType();
        int size = types.length;
        int errors = 0;
        StringBuilder returnMessage = new StringBuilder();
        for (Integer type : types) {
            ReturnVo return_vo = insert_link(project.getProjectId(), type, links);
            if (return_vo.getSuccess()) {
                returnMessage.append(return_vo.getMessage()).append("\n");
            } else {
                returnMessage.append(get_link_name(type)).append(":").append(return_vo.getMessage()).append("\n");
                errors++;
            }
        }
        if (errors == 0) {
            returnMessage.append("全部").append(size).append("条数据添加完成!未发送错误!\n");
        } else {
            returnMessage.append("添加").append(size).append("条完成!捕获").append(errors).append("条异常数据!");
        }

        // 因为缓存过加入数据之前的数据 直接释放
        redisTemplate.delete("project_links_" + project.getProjectId());

        return returnMessage.toString();
    }

    ReturnVo insert_link(Integer pid, Integer type, List<EasyLink> links) {

        ReturnVo result = new ReturnVo();

        if (links != null) {
            boolean exists = links.stream().anyMatch(link -> link.getType().equals(type));
            if (exists) {
                result.setSuccess(false);
                result.setMessage("项目已存在该类型链接");
                return result;
            }
        }

        // 完成程序查询
        EasyLink easyLink = new EasyLink();
        String link_key = StringUtil.generateRandomString(12);
        EasyLink easy_link_temp = get_link_by_key(link_key);

        // 重新生成key 虽然是随机的数据 但是也存在重复的可能性
        int errors = 0;
        while (easy_link_temp != null) {
            link_key = StringUtil.generateRandomString(12);
            easy_link_temp = get_link_by_key(link_key);
            log.info("key create errors:" + errors);
            errors++;
            if (errors > 3) {
                result.setSuccess(false);
                result.setMessage("生成项目key失败");
                return result;
            }
        }

        // 删除缓存
        easyLink.setLink(link_key);
        easyLink.setProjectId(pid);
        easyLink.setType(type);
        boolean success = linkMapper.insert(easyLink) > 0;
        if (!success) {
            result.setSuccess(false);
            result.setMessage("插入数据失败!");
            return result;
        }
        return new ReturnVo(true, get_link_name(type) + ": 新增成功!");
    }

    @Override
    public String get_link_name(Integer type) {
        return switch (type) {
            case 1 -> "单码卡密登录";
            case 2 -> "解绑或换机器码";
            case 3 -> "用户登录";
            case 4 -> "获取程序公告";
            case 5 -> "更新查询";
            case 6 -> "获取程序变量列表";
            case 7 -> "单码心跳";
            case 8 -> "用户心跳";
            default -> "未知类型";
        };
    }


    // 获取项目 根据用户ID
    @Override
    public EasyProject get_project_by_id_with_uid(Integer pid, Integer uid) {

        EasyProject project = get_project_by_id(pid);
        if (project == null)
            throw new RuntimeException("项目不存在");
        if (!Objects.equals(project.getProjectUser(), uid))
            throw new RuntimeException("该用户无权访问或修改");
        return project;
    }

    @Override
    public EasyProject get_project_by_id(Integer id) {
        EasyProject project = (EasyProject) redisTemplate.opsForValue().get("open_project_" + id);
        if (project != null) {
            log.info("redis get project temp {}", id);
            return project;
        }
        project = projectMapper.selectById(id);
        redisTemplate.opsForValue().set("open_project_" + id, project, 30, TimeUnit.MINUTES);
        return project;
    }

    @Override
    public EasyLink get_link_by_key(String key) {
        LambdaQueryWrapper<EasyLink> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyLink::getLink, key);
        return linkMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean update_project_link(ProjectLinkDTO projectLinkDTO) {
        Integer type = projectLinkDTO.getType()[0];
        if (type == null || type == 0)
            throw new RuntimeException("参数错误");
        EasyUser user = userService.get_user_by_jwt();
        EasyProject project = get_project_by_id_with_uid(projectLinkDTO.getProjectId(), user.getUserId());

        LambdaQueryWrapper<EasyLink> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyLink::getProjectId, project.getProjectId());
        queryWrapper.eq(EasyLink::getType, type);
        EasyLink easyLink = linkMapper.selectOne(queryWrapper);
        if (easyLink == null) {
            throw new RuntimeException("项目不存在该类型链接");
        }

        redisTemplate.delete("project_links_" + project.getProjectId());
        easyLink.setCode(projectLinkDTO.getCode());
        easyLink.setCodeType(projectLinkDTO.getCodeType());
        easyLink.setSafeType(projectLinkDTO.getSafeType());
        easyLink.setReturnTime(projectLinkDTO.getReturnTime());
        return linkMapper.updateById(easyLink) > 0;
    }

    @Override
    public boolean delete_project_link(Integer id) {
        EasyUser user = userService.get_user_by_jwt();
        EasyLink link = linkMapper.selectById(id);
        if (link == null)
            throw new RuntimeException("链接不存在");
        EasyProject project = get_project_by_id_with_uid(link.getProjectId(), user.getUserId());
        if (project == null)
            throw new RuntimeException("链接越权删除");
        redisTemplate.delete("project_links_" + project.getProjectId());
        return linkMapper.deleteById(id) > 0;
    }

    @Override
    public ReturnVo add_project_card(ProjectAddCardDTO projectAddCardDTO) {
        EasyUser user = userService.get_user_by_jwt();
        LocalDateTime localDateTime = null;
        if (projectAddCardDTO.getCardType()==7)
        {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(projectAddCardDTO.getEndTime());

                // 转换为系统默认时区的LocalDateTime
                localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            }catch (Exception e){
                log.info("时间格式错误：{}",e.getMessage());
                throw new RuntimeException("时间格式错误");
            }
        }

        EasyProject project = get_project_by_id_with_uid(projectAddCardDTO.getProjectId(), user.getUserId());
        List<EasyCard> list = new ArrayList<>();
        for (int i = 0;i<projectAddCardDTO.getCardNum();i++)
        {
            EasyCard card = new EasyCard();
            card.setCardKey(StringUtil.generateRandomKey());
            card.setCardType(projectAddCardDTO.getCardType());
            card.setCardTime(projectAddCardDTO.getCardTime());
            card.setPid(project.getProjectId());
            card.setUid(user.getUserId());
            card.setCreateTime(LocalDateTime.now());
            card.setIntroduction(projectAddCardDTO.getCardRemark());
            // 绑定时间 当用户创建卡密的时候选择的是自定义时间 即刻生效
            if (projectAddCardDTO.getCardType()==7)
                card.setEndTime(localDateTime);
            list.add(card);
        }
        cardService.saveBatch(list);
        StringBuilder result = new StringBuilder();
        for (EasyCard card : list) {
            result.append(card.getCardKey()).append("\n");
        }
        return new ReturnVo(true, result.toString());
    }
}
