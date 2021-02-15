package com.cl.fun.gag.pic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.cl.fun.gag.pic.dao")
public class GagPicApplication {

    static {
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    public static void main(String[] args) {

        SpringApplication.run(GagPicApplication.class, args);
    }

}
