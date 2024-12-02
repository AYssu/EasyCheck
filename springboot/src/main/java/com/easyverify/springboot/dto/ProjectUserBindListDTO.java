package com.easyverify.springboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectUserBindListDTO {
    @NotNull(message = "当前页码不能为空")
    private Integer currentPage; // 当前页码
    @NotNull(message = "每页显示数量不能为空")
    private Integer pageSize; // 每页显示数量
    private String projectName; // 项目名称
}
