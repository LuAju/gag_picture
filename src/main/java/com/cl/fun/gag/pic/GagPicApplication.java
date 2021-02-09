package com.cl.fun.gag.pic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GagPicApplication {

    public static void main(String[] args) {
        SpringApplication.run(GagPicApplication.class, args);
    }

}
