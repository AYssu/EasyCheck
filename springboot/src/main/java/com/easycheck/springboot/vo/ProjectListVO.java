package com.easycheck.springboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectListVO {
    private Integer projectId; // 项目ID

    private String projectName; // 项目名称

    @JsonFormat(pattern = "yyyy年MM月dd日")
    private LocalDateTime projectCreateTime; // 项目创建时间

    private String projectKey; // 项目密钥
    private String projectMessage; // 项目描述
    private Integer projectVersion; // 项目版本
    private String projectUpdateUrl; // 项目更新地址
    private String projectUpdateNotice; // 项目更新公告
    private String projectNotice; // 项目公告
    private String projectBase64; // 项目自定义BASE64
    private Integer projectStatus;// 项目状态
    private Integer freeStatus; // 项目是否免费
}
