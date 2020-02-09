package com.ccstay.ccstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.ccstay.ccstore.mapper")
public class CcstoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CcstoreApplication.class, args);
    }

    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}