package com.easyverify.springboot.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO implements UserDetails {
    private String token;// 登录成功返回token
    private String userName;// 用户名
    private String userEmail;// 邮箱
    private String userPhone;// 手机号
    private String userAvatarUrl;// 头像
    private Integer level;// 等级
    private String signature;// 签名


    // 密码返回为null
    @JSONField(serialize = false)
    private String userPassword;// 密码
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;// 注册时间
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    /*
    * 密码返回为null
    * 在这不得不提一下 使用jackson 和 fastjson2一起使用的麻烦事真的很多
    * 这边是配置好了 不过还没来得及整理 以后看到这路我应该回去重新整理
    * 到时候估计应该项目写的差不多的样子的时候了
     */
    @JsonIgnore
    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
