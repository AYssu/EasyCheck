package com.easyverify.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("easy_variable")
public class EasyVariable {
    @TableId(type = IdType.AUTO)
    private Integer vid; // 变量ID
    private Integer projectId; // 项目ID
    private String variableJson; // 变量JSON
}
