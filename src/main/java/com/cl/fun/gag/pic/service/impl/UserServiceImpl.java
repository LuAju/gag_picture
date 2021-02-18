package com.cl.fun.gag.pic.service.impl;

import com.cl.fun.gag.pic.dao.UserMapper;
import com.cl.fun.gag.pic.entity.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("");
        }
        user.setRoleList(userMapper.getRoleListByUsername(username));
        return user;
    }
}
