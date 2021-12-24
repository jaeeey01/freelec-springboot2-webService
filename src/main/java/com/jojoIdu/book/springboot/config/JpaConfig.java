package com.jojoIdu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    /*@EnableJpaAuditing
     *
     * Security 적용 중 HelloControllerTest.java 에서 에러발생.
     * @EnableJpaAuditing을 사용하기위해선 최소 하나의 @Entity가 필요하지만
     * @WebMvcTest에는 없음
     * @EnableJpaAuditing 이 @SpringBootApplication과 같이 있다보니
     * @WebMvcTest도 스캔함
     * 그래서 @EnableJpaAuditing과 @SpringBootApplication을 분리하기 위하여
     * Application.java에서 @EnableJpaAuditing 삭제 후 JpaConfig 생성
     * */
}
