package com.easyverify.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectUpdateDTO {
    @NotBlank(message = "项目ID不能为空")
    private Integer pid; // 程序ID
    @NotBlank(message = "更新ID不能为空")
    private Integer updateId; // 更新ID
    @NotNull(message = "版本号不能为空")
    private String updateVersion;// 版本号
    @NotBlank(message = "更新内容不能为空")
    private String updateMessage;// 更新内容
    @NotBlank(message = "下载地址不能为空")
    private String updateUrl;// 下载地址
    @NotNull(message = "是否强制更新不能为空")
    private Integer mustUpdate;// 是否强制更新
}
