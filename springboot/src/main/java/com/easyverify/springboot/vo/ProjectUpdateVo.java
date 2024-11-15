package com.easyverify.springboot.vo;

import lombok.Data;

@Data
public class ProjectUpdateVo {
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

}
