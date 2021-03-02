package com.cl.fun.gag.pic.dao;

import com.cl.fun.gag.pic.entity.UserPo;
import com.cl.fun.gag.pic.entity.auth.Role;
import com.cl.fun.gag.pic.entity.sql.UmsPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    UserPo getUserByUsername(String username);

    List<Role> getRoleListByUsername(String username);

    int insertUser(UserPo userPo);

    List<UmsPermission> getUserPermission(@Param("userId") Long userId);

}
