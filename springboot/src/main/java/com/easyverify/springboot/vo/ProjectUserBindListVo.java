package com.easyverify.springboot.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectUserBindListVo {
    // 用户ID
    private Integer openUserId;

    // 项目名称
    private String projectName;

    // 用户绑定时间
    @JSONField(format = "yyyy年MM月dd日")
    private LocalDateTime openCreateTime;

    // 用户邮箱
    private String openEmail;

    // 用户VIP等级，默认为1
    private Integer openVip;

    // 用户体验时长
    private LocalDateTime openSvipTime;
}
