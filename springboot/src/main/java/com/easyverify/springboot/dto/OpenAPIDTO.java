package com.easyverify.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OpenAPIDTO {
    @NotBlank(message = "前端配置异常")
    private String pid; // 程序ID
    @NotBlank(message = "前端配置异常")
    private String time;
    @NotBlank(message = "前端配置异常")
    private String sign;
}
