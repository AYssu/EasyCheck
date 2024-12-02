package com.easyverify.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectSetVariableDTO {
    @NotBlank(message = "项目ID不能为空")
    private Integer pid;
    private Object json;

}
