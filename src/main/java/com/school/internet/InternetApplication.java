package com.school.internet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.school.internet.*.mapper")
public class InternetApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternetApplication.class, args);
    }

}
