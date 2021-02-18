package com.cl.fun.gag.pic.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *  封装 登录用的账号密码对象
 *
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;

    private String password;

}
