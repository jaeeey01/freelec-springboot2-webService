package com.jojoIdu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing 삭제 후 JpaConfig로 이동
@SpringBootApplication// 자동 설정, bean read & create autoConfig
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
