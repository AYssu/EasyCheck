package com.easyverify.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("easy_link")
public class EasyLink {

    /**
     * API ID
     */
    @TableId(type = IdType.AUTO)
    private Integer aid;

    /**
     * 链接
     */
    private String link;

    /**
     * 程序ID
     */
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
    private Integer type;

    /**
     * 返回校验码
     */
    private Integer code;

    /**
     * code返回格式
     * 1. int 类型
     * 2. string 类型
     */
    private Integer codeType;

    /**
     * 安全传输
     * 1. 开启
     * 2. 关闭
     */
    private Integer safeType;

    /**
     * 返回时间戳
     * 1. 返回
     * 2. 不返回
     */
    private Integer returnTime;

    // 省略构造方法、getter和setter方法
}