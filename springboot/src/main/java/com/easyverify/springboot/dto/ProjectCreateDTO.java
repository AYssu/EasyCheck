package com.easyverify.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProjectCreateDTO {
    @NotBlank(message = "项目名不能为空")
    @Pattern(regexp = "^.{2,10}$", message = "长度在 2 到 10 个字符")
    private String projectName;
    @NotBlank(message = "项目备注不能为空")
    @Pattern(regexp = "^.{2,255}$", message = "长度在 2 到 255 个字符")
    private String projectMessage;
}
