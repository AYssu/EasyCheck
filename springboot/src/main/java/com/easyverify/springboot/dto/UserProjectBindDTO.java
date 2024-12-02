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
public class UserProjectBindDTO {
    /**
     * 用户邮箱，用户绑定的邮箱
     */
    @Email(message = "请输入正确的邮箱地址")
    public String email; // 邮箱地址
    /**
     * 验证码，用户登录使用的验证码
     */
    @NotBlank(message = "验证码不能为空")
    public String code;
    /**
     * 用户密码，用户登录使用的密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^.{6,18}$", message = "密码长度在 6 到 18 个字符")
    public String password; // 密码
    /**
     * 项目密钥，用户绑定的项目密钥
     */
    @NotBlank(message = "绑定密钥不能为空")
    public String bindKey; // 绑定密钥
}
