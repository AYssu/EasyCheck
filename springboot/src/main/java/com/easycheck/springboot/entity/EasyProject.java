package com.easycheck.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("easy_project")
public class EasyProject {
    @TableId(type = IdType.AUTO)
    private Integer projectId; // 项目ID

    private String projectUser;// 项目创建者
    private String projectName; // 项目名称

    private LocalDateTime projectCreateTime; // 项目创建时间

    private String projectKey; // 项目密钥
    private String projectMessage; // 项目描述
    private Integer projectVersion; // 项目版本
    private String projectUpdateUrl; // 项目更新地址
    private String projectUpdateNotice; // 项目更新公告
    private String projectNotice; // 项目公告
    private String projectBase64; // 项目自定义BASE64
}