package com.cl.fun.gag.pic.service.security;

import com.cl.fun.gag.pic.dao.UserMapper;
import com.cl.fun.gag.pic.entity.UserPo;
import com.cl.fun.gag.pic.entity.auth.UserManageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 *
 *  security内置接口的实现类
 *
 * */

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPo userPo = userMapper.getUserByUsername(username);
        if (userPo == null){
            throw new UsernameNotFoundException("");
        }
        // 这里要存进去
        UserManageDetails userManageDetails = new UserManageDetails(userPo, userMapper.getUserPermission(userPo.getId()));
        return userManageDetails;
    }
}
