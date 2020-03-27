package com.photo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.photo.web.mapper")
public class PhotowebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotowebApplication.class, args);
    }

}
