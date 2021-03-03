package com.cl.fun.gag.pic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

@MapperScan("com.cl.fun.gag.pic.dao")
// 需要添加这个注解，否则鉴权注解无效
@EnableGlobalMethodSecurity(prePostEnabled = true)

@EnableTransactionManagement
public class GagPicApplication {

    // 解决redis和es版本冲突时的报错
    static {
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    public static void main(String[] args) {

        SpringApplication.run(GagPicApplication.class, args);
    }

}
