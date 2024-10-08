package com.easycheck.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO {
    private String token;// 登录成功返回token
    private String userName;// 用户名
    private String userEmail;// 邮箱
    private String userPhone;// 手机号
    private String userAvatarUrl;// 头像
    private Integer level;// 等级
    private String signature;// 签名
}
