package org.workp.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.workp")
@MapperScan("org.workp.core.infrastructure.mapper")
public class IBatisToolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(IBatisToolsApplication.class, args);
    }
}
