package com.easyverify.springboot.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easyverify.springboot.dto.OpenAPIDTO;
import com.easyverify.springboot.dto.UserProjectBindDTO;
import com.easyverify.springboot.entity.EasyLink;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.entity.EasyUser;
import com.easyverify.springboot.entity.OpenUser;
import com.easyverify.springboot.mapper.OpenUserMapper;
import com.easyverify.springboot.service.OpenAPIService;
import com.easyverify.springboot.service.ProjectService;
import com.easyverify.springboot.service.SendMailService;
import com.easyverify.springboot.service.UserService;
import com.easyverify.springboot.utils.MD5Util;
import com.easyverify.springboot.utils.Sutils;
import com.easyverify.springboot.utils.Bcrypt;
import com.easyverify.springboot.vo.ResponseResult;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OpenAPIServiceImpl implements OpenAPIService {
    // 自定义加密base64
    @Value("${encrypt.base64}")
    public String encrypt_base64;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private OpenUserMapper openUserMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public boolean user_project_bind(UserProjectBindDTO userProjectBindDTO) {

        Sutils.set_base64(encrypt_base64);
        String bind_url = userProjectBindDTO.getBindKey();
        String project_id = "";
        try {
            project_id = Sutils.base64_decode(bind_url);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        log.info("user bind: {}",project_id);

        EasyProject project = projectService.get_project_by_id(Integer.parseInt(project_id));
        if (project == null)
        {
            throw new RuntimeException("绑定项目不存在");
        }

        OpenUser openUser = get_user_by_email(userProjectBindDTO.getEmail());
        if (openUser !=null)
        {
            throw new RuntimeException("用户已注册,请前往控制台绑定");
        }

        // 判断验证码是否正确 验证码校验要求低 确保绑定程序以及open user 不存在 优先级高一些
        String code = (String) redisTemplate.opsForValue().get(userProjectBindDTO.getEmail()+"_code");
        if (code == null||!code.equals(userProjectBindDTO.getCode())) {
            throw new RuntimeException("验证码错误");
        }


        openUser = new OpenUser();
        openUser.setOpenEmail(userProjectBindDTO.getEmail());

        // 密码加密
        String salt = Bcrypt.gensalt(10, new SecureRandom());

        // 加密
        String encoded = Bcrypt.hashpw(userProjectBindDTO.getPassword(), salt);

        openUser.setOpenPassword(encoded);
        openUser.setOpenCreateTime(LocalDateTime.now());

        boolean success = openUserMapper.insert(openUser) > 0;
        if (!success)
            throw new RuntimeException("用户注册失败");

        OpenUser user = get_user_by_email(userProjectBindDTO.getEmail());

        return openUserMapper.insert_user_project(user.getOpenUserId(), Integer.parseInt(project_id)) > 0;
    }


    @Override
    public OpenUser get_user_by_email(String email) {
        LambdaQueryWrapper<OpenUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OpenUser::getOpenEmail, email);
        return openUserMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean send_register_code_mail(String email) {

        EasyUser user = userService.get_user_by_email(email);
        if (user != null)
            throw new RuntimeException("用户已注册");
        // 判断是否已发送
        String code = (String) redisTemplate.opsForValue().get(email+"_code");
        if (code != null) {
            Long expire = redisTemplate.getExpire(email + "_code", TimeUnit.SECONDS);
            throw new RuntimeException("请于" + expire + "秒后再试");
        }
        try {
            int random_code = (int)((Math.random()*9+1)*100000);
            sendMailService.sendCodeMail(email,random_code);
            redisTemplate.opsForValue().set(email+"_code", Integer.toString(random_code),5, TimeUnit.MINUTES);
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EasyLink open_link_type(int pid, String base) {
        List<EasyLink> links = projectService.get_project_links_redis(pid);
        if (links.isEmpty())
            return null;

        for (EasyLink link : links) {
            if (link.getLink().equals(base)) {
                return link;
            }
        }
        return null;
    }

    @Override
    public ResponseResult<?> get_project_notice(EasyProject project, OpenAPIDTO openAPIDTO, EasyLink link) {
        String origin = "pid="+openAPIDTO.getPid()+"&time="+openAPIDTO.getTime()+"&"+project.getProjectKey();
        String md5_origin = MD5Util.getMD5(Sutils.base64_encode(origin,project.getProjectBase64())).toUpperCase();
        String sign = Sutils.hex_to_string(openAPIDTO.getSign()).toUpperCase();
        log.info("md5: {} vs {}",md5_origin,sign);
        if (!md5_origin.equals(sign))
        {
            return ResponseResult.fail("客户端被篡改!");
        }
        // 获取程序公告

        long now_time = System.currentTimeMillis() / 1000;
        String notice = project.getProjectNotice();
        Integer code = link.getCode();

        JSONObject json = new JSONObject();
        json.put("code",link.getCodeType()==1?code:code.toString());
        json.put("notice",notice);
        if (link.getReturnTime()==1)
            json.put("time",now_time);
        if (link.getSafeType()!=1)
            return ResponseResult.success("获取成功",json);
        log.info("公告 json 对象: {}",json.toJSONString());
        String safe_result = Sutils.to_hex(Sutils.base64_encode(json.toJSONString(),project.getProjectBase64()));
        return ResponseResult.success("获取成功",safe_result);
    }
}
