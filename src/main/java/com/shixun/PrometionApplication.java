package com.shixun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.shixun.api.mapper")
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement   //开启事务支持，之后可以使用@Transactiona
public class PrometionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrometionApplication.class, args);
    }
}
