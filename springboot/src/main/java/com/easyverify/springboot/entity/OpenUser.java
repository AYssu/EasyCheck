package com.easyverify.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表实体类
 */
@Data
@TableName("open_user") // 指定数据库中的表名
public class OpenUser {

    // 用户ID，使用@TableId注解指定为主键，自动增长类型
    @TableId(value = "open_user_id", type = IdType.AUTO)
    private Integer openUserId;

    // 用户邮箱
    private String openEmail;

    // 用户密码
    private String openPassword;

    // 用户体验时长
    private LocalDateTime openSvipTime;

    // 用户体验时间，每天一次
    private LocalDateTime openLastSoloTime;

    // 用户VIP等级，默认为1
    private Integer openVip;

    // 用户绑定时间
    private LocalDateTime openCreateTime;

    // 是否删除，用于软删除
    private Integer deleted;
}