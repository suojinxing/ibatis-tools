package org.workp.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("org.workp.core.infrastructure.repository.mapper")
public class IBatisToolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(IBatisToolsApplication.class, args);
    }
}
