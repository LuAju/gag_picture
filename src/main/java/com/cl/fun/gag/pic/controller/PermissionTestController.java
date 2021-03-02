package com.cl.fun.gag.pic.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionTestController {
    @RequestMapping("/test")
    public Object get(){
        return "aa";
    }

    @PreAuthorize("hasAuthority('pms:product:create')")
    @RequestMapping("/testPermission")
    public Object geta(){
        return "aaa";
    }

    @PreAuthorize("hasAuthority('pms:product:createff')")
    @RequestMapping("/testPermissionfff")
    public Object getaff(){
        return "fff";
    }

    @PreAuthorize("false")
    @RequestMapping("/testPermissionFail")
    public Object getFail(){
        return "Fail";
    }

    @PreAuthorize("true")
    @RequestMapping("/testPermissionTrue")
    public Object getTrue(){
        return "True";
    }
}
