package com.easyverify.springboot.service.impl;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easyverify.springboot.dto.OpenAPIDTO;
import com.easyverify.springboot.dto.UserProjectBindDTO;
import com.easyverify.springboot.entity.*;
import com.easyverify.springboot.mapper.OpenUserMapper;
import com.easyverify.springboot.mapper.VariableMapper;
import com.easyverify.springboot.service.OpenAPIService;
import com.easyverify.springboot.service.ProjectService;
import com.easyverify.springboot.service.SendMailService;
import com.easyverify.springboot.service.UserService;
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
    private VariableMapper variableMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean user_project_bind(UserProjectBindDTO userProjectBindDTO) {

        Sutils.set_base64(encrypt_base64);
        String bind_url = userProjectBindDTO.getBindKey();
        String project_id = "";
        try {
            project_id = Sutils.base64_decode(bind_url);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        log.info("user bind: {}", project_id);

        EasyProject project = projectService.get_project_by_id(Integer.parseInt(project_id));
        if (project == null) {
            throw new RuntimeException("绑定项目不存在");
        }

        OpenUser openUser = get_user_by_email(userProjectBindDTO.getEmail());
        if (openUser != null) {
            throw new RuntimeException("用户已注册,请前往控制台绑定");
        }

        // 判断验证码是否正确 验证码校验要求低 确保绑定程序以及open user 不存在 优先级高一些
        String code = (String) redisTemplate.opsForValue().get(userProjectBindDTO.getEmail() + "_code");
        if (code == null || !code.equals(userProjectBindDTO.getCode())) {
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
        String code = (String) redisTemplate.opsForValue().get(email + "_code");
        if (code != null) {
            Long expire = redisTemplate.getExpire(email + "_code", TimeUnit.SECONDS);
            throw new RuntimeException("请于" + expire + "秒后再试");
        }
        try {
            int random_code = (int) ((Math.random() * 9 + 1) * 100000);
            sendMailService.sendCodeMail(email, random_code);
            redisTemplate.opsForValue().set(email + "_code", Integer.toString(random_code), 5, TimeUnit.MINUTES);
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
    public ResponseResult<?> get_project_notice(EasyProject project, OpenAPIDTO openAPIDTO, EasyLink link, RSA rsa) {

        JSONObject result_object;
        try {
            long begin_time = System.currentTimeMillis();
            long now_time = System.currentTimeMillis() / 1000;

            String send_time;
            if (project.getProjectEncryption() == 1) {
                send_time = Sutils.base64_decode(Sutils.hex_to_string(openAPIDTO.getTime()), project.getProjectBase64());

            } else if (project.getProjectEncryption() == 2) {

                send_time = rsa.decryptStr(Sutils.hex_to_string(openAPIDTO.getTime()), KeyType.PrivateKey);

            } else {
                throw new RuntimeException("加密方式异常");
            }
            long alive_time = Math.abs(now_time - Long.parseLong(send_time));
            log.info("alive_time: {}", alive_time);
            if (alive_time > 10) {
                return ResponseResult.fail("客户端请求超时: " + alive_time);
            }

            String origin = "pid=" + openAPIDTO.getPid() + "&time=" + openAPIDTO.getTime() + "&" + project.getProjectKey();

            String md5_origin = "";
            if (project.getProjectEncryption() == 1)
                md5_origin = DigestUtil.sha256Hex(Sutils.base64_encode(origin, project.getProjectBase64())).toUpperCase();
            else if (project.getProjectEncryption() == 2)
                md5_origin = DigestUtil.sha256Hex(Sutils.base64_encode(origin)).toUpperCase();
            String sign = Sutils.hex_to_string(openAPIDTO.getSign()).toUpperCase();
            if (!md5_origin.equals(sign)) {
                return ResponseResult.fail("客户端被篡改!");
            }
            // 获取程序公告

            now_time = System.currentTimeMillis() / 1000;
            String notice = project.getProjectNotice();
            Integer code = link.getCode();

            JSONObject json = new JSONObject();
            json.put("code", link.getCodeType() == 1 ? code : code.toString());
            json.put("notice", notice);
            if (link.getReturnTime() == 1)
                json.put("time", now_time);
            if (link.getSafeType() != 1)
                return ResponseResult.success("获取成功", json);

            String safe_result = "";
            String token = "";
            if (project.getProjectEncryption() == 1) {
                safe_result = Sutils.to_hex(Sutils.base64_encode(json.toJSONString(), project.getProjectBase64()));

                token = DigestUtil.sha256Hex(Sutils.base64_encode(safe_result + "&" + project.getProjectKey(), project.getProjectBase64())).toUpperCase();

            } else if (project.getProjectEncryption() == 2) {
                safe_result = Sutils.to_hex(rsa.encryptBase64(json.toJSONString(), KeyType.PrivateKey));

                token = DigestUtil.sha256Hex(Sutils.base64_encode(safe_result + "&" + project.getProjectKey())).toUpperCase();
            }
            result_object = new JSONObject();
            result_object.put("data", safe_result);
            result_object.put("token", token);

            long end_time = System.currentTimeMillis();
            log.info("get_project_notice: {}ms", (end_time - begin_time));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseResult.fail("客户端解析异常");
        }

        return ResponseResult.success("获取成功", result_object);
    }

    @Override
    public ResponseResult<?> get_project_update(EasyProject project, OpenAPIDTO openAPIDTO, EasyLink link, RSA rsa) {

        JSONObject result_object;
        try {
            long begin_time = System.currentTimeMillis();
            long now_time = System.currentTimeMillis() / 1000;

            String send_time;
            if (project.getProjectEncryption() == 1) {
                send_time = Sutils.base64_decode(Sutils.hex_to_string(openAPIDTO.getTime()), project.getProjectBase64());

            } else if (project.getProjectEncryption() == 2) {

                send_time = rsa.decryptStr(Sutils.hex_to_string(openAPIDTO.getTime()), KeyType.PrivateKey);

            } else {
                throw new RuntimeException("加密方式异常");
            }
            String send_version;
            if (project.getProjectEncryption() == 1) {
                send_version = Sutils.base64_decode(Sutils.hex_to_string(openAPIDTO.getVersion()), project.getProjectBase64());

            } else if (project.getProjectEncryption() == 2) {

                send_version = rsa.decryptStr(Sutils.hex_to_string(openAPIDTO.getVersion()), KeyType.PrivateKey);

            } else {
                throw new RuntimeException("加密方式异常");
            }
            long alive_time = Math.abs(now_time - Long.parseLong(send_time));
            log.info("alive_time: {}", alive_time);
            if (alive_time > 10) {
                return ResponseResult.fail("客户端请求超时: " + alive_time);
            }

            String origin = "pid=" + openAPIDTO.getPid()  +  "&version=" + openAPIDTO.getVersion() + "&time=" + openAPIDTO.getTime() + "&" + project.getProjectKey();

            String md5_origin = "";
            if (project.getProjectEncryption() == 1)
                md5_origin = DigestUtil.sha256Hex(Sutils.base64_encode(origin, project.getProjectBase64())).toUpperCase();
            else if (project.getProjectEncryption() == 2)
                md5_origin = DigestUtil.sha256Hex(Sutils.base64_encode(origin)).toUpperCase();
            String sign = Sutils.hex_to_string(openAPIDTO.getSign()).toUpperCase();
            if (!md5_origin.equals(sign)) {
                return ResponseResult.fail("客户端被篡改!");
            }
            // 获取程序公告

            EasyProjectUpdate update = projectService.get_project_update_by_id(project.getProjectId());
            if (update == null) {
                return ResponseResult.fail("程序还未开启更新设置");
            }


            now_time = System.currentTimeMillis() / 1000;
            String notice = project.getProjectNotice();
            Integer code = link.getCode();

            JSONObject json = new JSONObject();
            json.put("code", link.getCodeType() == 1 ? code : code.toString());

            if (update.getUpdateVersion().equals(send_version)) {
                // 不需要更新
                json.put("update_status", false);
                if (project.getReturnUpdate()==2)
                {
                    json.put("notice", notice);
                }
            }else
            {
                // 需要更新
                json.put("update_status", true);

                json.put("update_must", update.getMustUpdate() == 2);
                json.put("update_url",update.getUpdateUrl());
                json.put("update_message",update.getUpdateMessage());
            }

            if (link.getReturnTime() == 1)
                json.put("time", now_time);
            if (link.getSafeType() != 1)
                return ResponseResult.success("获取成功", json);

            String safe_result = "";
            String token = "";
            if (project.getProjectEncryption() == 1) {
                safe_result = Sutils.to_hex(Sutils.base64_encode(json.toJSONString(), project.getProjectBase64()));

                token = DigestUtil.sha256Hex(Sutils.base64_encode(safe_result + "&" + project.getProjectKey(), project.getProjectBase64())).toUpperCase();

            } else if (project.getProjectEncryption() == 2) {
                safe_result = Sutils.to_hex(rsa.encryptBase64(json.toJSONString(), KeyType.PrivateKey));

                token = DigestUtil.sha256Hex(Sutils.base64_encode(safe_result + "&" + project.getProjectKey())).toUpperCase();
            }
            result_object = new JSONObject();
            result_object.put("data", safe_result);
            result_object.put("token", token);

            long end_time = System.currentTimeMillis();
            log.info("get_project_notice: {}ms", (end_time - begin_time));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseResult.fail("客户端解析异常");
        }

        return ResponseResult.success("获取成功", result_object);
    }

    @Override
    public ResponseResult<?> get_project_variable(EasyProject project, OpenAPIDTO openAPIDTO, EasyLink link, RSA rsa) {

        JSONObject result_object;
        try {
            long begin_time = System.currentTimeMillis();
            long now_time = System.currentTimeMillis() / 1000;

            String send_time;
            if (project.getProjectEncryption() == 1) {
                send_time = Sutils.base64_decode(Sutils.hex_to_string(openAPIDTO.getTime()), project.getProjectBase64());

            } else if (project.getProjectEncryption() == 2) {

                send_time = rsa.decryptStr(Sutils.hex_to_string(openAPIDTO.getTime()), KeyType.PrivateKey);

            } else {
                throw new RuntimeException("加密方式异常");
            }
            long alive_time = Math.abs(now_time - Long.parseLong(send_time));
            log.info("alive_time: {}", alive_time);
            if (alive_time > 10) {
                return ResponseResult.fail("客户端请求超时: " + alive_time);
            }

            String origin = "pid=" + openAPIDTO.getPid() + "&time=" + openAPIDTO.getTime() + "&" + project.getProjectKey();

            String md5_origin = "";
            if (project.getProjectEncryption() == 1)
                md5_origin = DigestUtil.sha256Hex(Sutils.base64_encode(origin, project.getProjectBase64())).toUpperCase();
            else if (project.getProjectEncryption() == 2)
                md5_origin = DigestUtil.sha256Hex(Sutils.base64_encode(origin)).toUpperCase();
            String sign = Sutils.hex_to_string(openAPIDTO.getSign()).toUpperCase();
            if (!md5_origin.equals(sign)) {
                return ResponseResult.fail("客户端被篡改!");
            }
            // 获取程序公告


            now_time = System.currentTimeMillis() / 1000;

            Integer code = link.getCode();

            String avs = projectService.get_project_variable_pid_redis(project.getProjectId());
            JSONObject json = new JSONObject();
            json.put("code", link.getCodeType() == 1 ? code : code.toString());
            json.put("variables", avs);
            if (link.getReturnTime() == 1)
                json.put("time", now_time);
            if (link.getSafeType() != 1)
                return ResponseResult.success("获取成功", json);

            String safe_result = "";
            String token = "";
            if (project.getProjectEncryption() == 1) {
                safe_result = Sutils.to_hex(Sutils.base64_encode(json.toJSONString(), project.getProjectBase64()));

                token = DigestUtil.sha256Hex(Sutils.base64_encode(safe_result + "&" + project.getProjectKey(), project.getProjectBase64())).toUpperCase();

            } else if (project.getProjectEncryption() == 2) {
                safe_result = Sutils.to_hex(rsa.encryptBase64(json.toJSONString(), KeyType.PrivateKey));

                token = DigestUtil.sha256Hex(Sutils.base64_encode(safe_result + "&" + project.getProjectKey())).toUpperCase();
            }
            result_object = new JSONObject();
            result_object.put("data", safe_result);
            result_object.put("token", token);

            long end_time = System.currentTimeMillis();
            log.info("get_project_notice: {}ms", (end_time - begin_time));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseResult.fail("客户端解析异常");
        }

        return ResponseResult.success("获取成功", result_object);
    }

}
