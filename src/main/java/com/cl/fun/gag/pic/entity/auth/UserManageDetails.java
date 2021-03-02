package com.cl.fun.gag.pic.entity.auth;

import com.cl.fun.gag.pic.entity.UserPo;
import com.cl.fun.gag.pic.entity.sql.UmsPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserManageDetails implements UserDetails {

    /** 数据库的user对象 */
    private UserPo userPo;

    private List<UmsPermission> permissionList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissionList.stream()
                // 保留符合过滤条件的
                .filter(permission->permission.getValue()!=null)
                // 获取所有的权限字符串，并封装
                .map(permission->new SimpleGrantedAuthority(permission.getValue()))
                // 转成collection
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userPo.getPassword();
    }

    @Override
    public String getUsername() {
        return userPo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
