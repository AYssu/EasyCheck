package com.easyverify.springboot.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("easy_user")
public class EasyUser {

    @TableId(type = IdType.AUTO)
    private Integer userId;// 主键 用户id
    private String userName;// 用户名
    private String userEmail;// 邮箱
    private String userPhone;// 手机号

    private String userPassword;// 密码
    private String userAvatarUrl;// 头像
    private Integer level;// 等级
    private String signature;// 签名


    private LocalDateTime registerTime;// 注册时间

    @TableLogic(value = "0", delval = "1")
    private Integer deleted; // 是否删除

}

