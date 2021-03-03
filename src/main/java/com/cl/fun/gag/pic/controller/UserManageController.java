package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.entity.UserPo;
import com.cl.fun.gag.pic.entity.auth.UserDto;
import com.cl.fun.gag.pic.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ums")
@Api("用户接口")
public class UserManageController {
    @Autowired
    private UserServiceImpl userService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @PostMapping("/login")
    @ApiOperation("用户登录接口,测试账号admin 密码123456")
    public Object login(@RequestBody UserDto userDto){
        String token = userService.login(userDto.getUsername(), userDto.getPassword());
        if (token == null) {
            return CommonResult.fail("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @PostMapping("/register")
    @ApiOperation("注册接口")
    public Object register(@RequestBody UserPo userPo){
        UserPo register = userService.register(userPo);
        if (register == null){
            return CommonResult.fail("创建用户失败");
        }
        return CommonResult.success("创建用户成功") ;
    }

    @GetMapping("/usernameDuplicate/{username}")
    @ApiOperation("获取用户名")
    public Object usernameDuplicate(@PathVariable String username){

        Boolean isDuplicate = userService.usernameDuplicate(username);
        if (isDuplicate){
            return CommonResult.fail("当前用户名不可用");
        }
        return CommonResult.success("当前用户名可用") ;
    }
}
