package com.easyverify.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyCard {
    /**
     * 卡密ID
     */
    @TableId(type = IdType.AUTO)
    private Integer cid;

    /**
     * 程序ID
     */
    private Integer pid;

    /**
     * 创建用户ID
     */
    private Integer uid;

    /**
     * 卡密字段
     */
    private String cardKey;

    /**
     * 卡密类型
     * 1.天卡
     * 2.周卡
     * 3.月卡
     * 4.半年卡
     * 5.年卡
     * 6.永久卡
     * 7.自定义时间卡
     */
    private Integer cardType;

    /**
     * 卡密倍率
     */
    private Integer cardTime;
    /**
     * 绑定设备标识
     */
    private String bindImei;

    /**
     * 最后登录IP
     */
    private String bindIp;

    /**
     * 第一次登录时间
     */
    private LocalDateTime firstBindTime;

    /**
     * 第一次创建卡密时间
     */
    private LocalDateTime createTime;

    /**
     * 最后一次登录时间
     */
    private LocalDateTime lastUseTime;

    /**
     * 使用次数
     */
    private Integer useNumber;

    /**
     * 解绑次数
     */
    private Integer unbindNumber;

    /**
     * 是否开启设备码验证
     */
    private Integer imeiCheck;

    /**
     * 是否开启网络IP验证
     */
    private Integer ipCheck;

    /**
     * 卡密备注
     */
    private String introduction;

    /**
     * 核心数据，卡密标记内容
     */
    private String coreDate;

    /**
     * 限制使用次数
     */
    private Integer limitUseNumber;

    /**
     * 限制解绑次数
     */
    private Integer limitUnbindNumber;

    /**
     * 是否拉黑
     */
    private Integer state;

    /**
     * 逻辑删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer delete;
}
