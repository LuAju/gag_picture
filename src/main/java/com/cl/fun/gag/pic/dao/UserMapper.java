package com.cl.fun.gag.pic.dao;

import com.cl.fun.gag.pic.entity.auth.Role;
import com.cl.fun.gag.pic.entity.auth.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByUsername(String username);

    List<Role> getRoleListByUsername(String username);
}
