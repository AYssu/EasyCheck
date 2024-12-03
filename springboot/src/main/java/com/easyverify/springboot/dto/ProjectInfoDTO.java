package com.easyverify.springboot.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfoDTO {
    @NotNull(message = "项目id不能为空")
    private Integer projectId; // 项目id
    @NotBlank(message = "项目名不能为空")
    @Pattern(regexp = "^.{2,10}$", message = "长度在 2 到 10 个字符")
    private String projectName; // 项目名

    private String projectMessage; // 项目备注

    @NotNull(message = "项目更新方式不能为空")
    @Min(value = 1, message = "输入异常")
    @Max(value = 2, message = "输入异常")
    private Integer returnUpdate; // 验证程序更新为最新版本返回公告

    @NotNull(message = "项目更新方式不能为空")

    @Min(value = 1, message = "输入异常")
    @Max(value = 2, message = "输入异常")
    private Integer returnVerify; // 用户登录或卡密登录成功返回变量
    @NotNull(message = "项目更新方式不能为空")
    @Min(value = 1, message = "输入异常")
    @Max(value = 9, message = "输入异常")
    private Integer projectEncryption; // 程序加密方式 1. base64自定义编码 2. rsa非对称加密

    private String projectRsaPrivate;// 项目RSA私钥
    private String projectRsaPublic;// 项目RSA公钥
}
