package com.easyverify.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("easy_project_update")
public class EasyProjectUpdate {
    /**
     * 更新ID
     */
    @TableId(type = IdType.AUTO)
    private Integer updateId;

    /**
     * 程序绑定ID
     */
    private Integer projectId;

    /**
     * 程序更新版本
     */
    private String updateVersion;

    /**
     * 更新公告
     */
    private String updateMessage;

    /**
     * 更新链接
     */
    private String updateUrl;

    /**
     * 是否强制更新
     */
    private Integer mustUpdate;

    /**
     * 逻辑删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
}
