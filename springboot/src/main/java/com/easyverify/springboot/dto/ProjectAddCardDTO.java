package com.easyverify.springboot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectAddCardDTO {
    @NotNull(message = "项目ID不能为空")
    private Integer projectId; // 项目ID
    @NotNull(message = "用户ID不能为空")
    @Max(value = 7, message = "卡密类型错误")
    @Min(value = 1, message = "卡密类型错误")
    private Integer cardType; // 卡片类型
    @NotNull(message = "卡密数量不能为空")
    @Max(value = 100, message = "卡密倍数不能超过100")
    @Min(value = 1, message = "卡密倍数不能小于1")
    private Integer cardTime; // 卡密倍数
    private String endTime; // 卡片到期时间
    @NotNull(message = "生成数量不能为空")
    @Max(value = 200, message = "单次生成数量不能超过200")
    @Min(value = 1, message = "单次生成数量不能小于1")
    private Integer cardNum; // 卡片数量
    private String cardRemark; // 卡片备注
}
