package com.jojoIdu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication// 자동 설정, bean read & create autoConfig
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
