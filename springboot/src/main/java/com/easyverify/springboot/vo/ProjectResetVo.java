package com.easyverify.springboot.vo;

import lombok.Data;

@Data
public class ProjectResetVo {
    private boolean success; // 是否成功
    private String projectBase64; // 项目自定义BASE64
    private String projectKey; // 项目密钥
}
