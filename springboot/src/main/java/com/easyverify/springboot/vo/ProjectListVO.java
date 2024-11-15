package com.easyverify.springboot.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectListVO {
    private Integer projectId; // 项目ID

    private String projectName; // 项目名称

    @JSONField(format = "yyyy年MM月dd日")
    private LocalDateTime projectCreateTime; // 项目创建时间

    private String projectKey; // 项目密钥
    private String projectMessage; // 项目描述
    private String projectNotice; // 项目公告
    private String projectBase64; // 项目自定义BASE64
    private Integer projectStatus;// 项目状态
    private Integer freeStatus; // 项目是否免费
    private String bindKey; // 绑定码
}
