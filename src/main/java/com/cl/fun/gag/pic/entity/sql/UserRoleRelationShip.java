package com.cl.fun.gag.pic.entity.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRelationShip {
    private Long id;

    private Long adminId;

    private Long roleId;
}
