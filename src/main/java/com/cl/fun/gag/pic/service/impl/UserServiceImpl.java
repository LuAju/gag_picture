package com.cl.fun.gag.pic.service.impl;

import com.cl.fun.gag.pic.customizeexception.NameDuplicateException;
import com.cl.fun.gag.pic.dao.UserMapper;
import com.cl.fun.gag.pic.dao.UserRoleDao;
import com.cl.fun.gag.pic.entity.UserPo;
import com.cl.fun.gag.pic.entity.sql.UserRoleRelationShip;
import com.cl.fun.gag.pic.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleDao userRoleDao;


    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    // 使用事务，插入用户关系失败时，取消新建用户的操作
    @Transactional
    public UserPo register(UserPo userPoParam){
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userPoParam,userPo);
        // 如果当前已经有了,直接抛异常
        if (userMapper.getUserByUsername(userPo.getUsername())!=null){
            throw new NameDuplicateException();
        }
        userPo.setPassword(passwordEncoder.encode(userPo.getPassword()));
        userPo.setCreateTime(new Date());
        userPo.setStatus(1);
        int i = userMapper.insertUser(userPo);
        if (i == 1) {
            // 获取插入后的主键id
            Long id = userPo.getId();
            UserRoleRelationShip userRoleRelationShip = new UserRoleRelationShip();
            userRoleRelationShip.setAdminId(id);
            userRoleRelationShip.setRoleId(2L);
            int insert = userRoleDao.insert(userRoleRelationShip);
            if (insert ==1) {
                return userPo;
            }
            return null;
        }
        return null;
    }

    public boolean usernameDuplicate(String username){
        // 如果当前已经有了,直接抛异常
        if (userMapper.getUserByUsername(username)!=null){
            return true;
        }
        return false;
    }


}
