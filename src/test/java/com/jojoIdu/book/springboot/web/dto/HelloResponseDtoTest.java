package com.jojoIdu.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        //assertThat : 테스트 검증라이브러리 검증 메소드
        //검증하고 싶은대상을 메소드 인자로 받음
        //메소드 체이닝 지원 되어 isEqualTo와 같이 메소드 이어서 사용가능
        //isEqualTo: assertj의 동등비교 메소드
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}