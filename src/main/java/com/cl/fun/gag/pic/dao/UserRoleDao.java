package com.cl.fun.gag.pic.dao;

import com.cl.fun.gag.pic.entity.sql.UserRoleRelationShip;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao {
    int insert(UserRoleRelationShip userRoleRelationShip);
}
