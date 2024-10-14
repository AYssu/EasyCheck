package com.easycheck.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectCreateDTO {
    @NotBlank(message = "项目名不能为空")
    private String projectName;
    private String projectMessage;
}
