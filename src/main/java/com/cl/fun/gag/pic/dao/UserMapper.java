package com.cl.fun.gag.pic.dao;

import com.cl.fun.gag.pic.entity.UserPo;
import com.cl.fun.gag.pic.entity.auth.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserPo getUserByUsername(String username);

    List<Role> getRoleListByUsername(String username);
}
