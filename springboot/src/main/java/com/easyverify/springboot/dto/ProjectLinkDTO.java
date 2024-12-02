package com.easyverify.springboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectLinkDTO {


    /**
     * 程序ID
     */
    @NotNull(message = "项目ID不能为空")
    private Integer projectId;

    /**
     * 链接格式
     * 1.单码卡密登录
     * 2.解绑或换机器码
     * 3.用户登录
     * 4.获取程序公告
     * 5.更新查询
     * 6.获取程序变量列表
     * 7.单码心跳
     * 8.用户心跳
     */
    @NotNull(message = "链接类型不能为空")
    private Integer[] type;

    /**
     * 返回校验码
     */
    @NotNull(message = "返回校验码不能为空")
    private Integer code;

    /**
     * code返回格式
     * 1. int 类型
     * 2. string 类型
     */
    @NotNull(message = "code返回格式不能为空")
    private Integer codeType;

    /**
     * 安全传输
     * 1. 开启
     * 2. 关闭
     */
    @NotNull(message = "安全传输不能为空")
    private Integer safeType;

    /**
     * 返回时间戳
     * 1. 返回
     * 2. 不返回
     */
    @NotNull(message = "返回时间戳不能为空")
    private Integer returnTime;
}
