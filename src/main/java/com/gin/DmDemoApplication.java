package com.gin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.gin.dao")
public class DmDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmDemoApplication.class, args);
    }

}
