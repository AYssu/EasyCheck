package com.easyverify.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectUpdateNoticeDTO {
    @NotNull(message = "项目id不能为空")
    private Integer pid; // 项目id
    @NotBlank(message = "通知内容不能为空")
    private String notice; // 通知内容
}
