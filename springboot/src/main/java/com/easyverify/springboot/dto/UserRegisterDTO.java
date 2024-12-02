package com.easyverify.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    /**
     * 用户账号，用户注册使用的账号
     */
    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "^.{2,10}$", message = "账号长度在 2 到 10 个字符")
    private String username;

    /**
     * 用户邮箱，用户绑定的邮箱
     */
    @Email(message = "请输入正确的邮箱地址")
    private String email;

    /**
     * 用户密码，用户登录使用的密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^.{6,18}$", message = "密码长度在 6 到 18 个字符")
    private String password;

    /**
     * 邮箱验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;
}