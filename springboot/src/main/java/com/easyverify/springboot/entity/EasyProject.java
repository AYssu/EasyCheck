package com.easyverify.springboot.entity;

import com.baomidou.mybatisplus.annotation.*;
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

    private Integer projectUser;// 项目创建者
    private String projectName; // 项目名称

    private LocalDateTime projectCreateTime; // 项目创建时间

    private String projectKey; // 项目密钥
    private String projectMessage; // 项目描述
    private String projectNotice; // 项目公告
    private String projectBase64; // 项目自定义BASE64
    private Integer projectStatus;// 项目状态
    private Integer freeStatus; // 项目是否免费

    @TableLogic(value = "0", delval = "1")
    private Integer deleted; // 是否删除

}