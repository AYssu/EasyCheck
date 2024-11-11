package com.easyverify.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "项目备注不能为空")
    @Pattern(regexp = "^.{2,255}$", message = "长度在 2 到 255 个字符")
    private String projectMessage; // 项目备注
}
