package com.jojoIdu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter// 모든필드 get메소드 생성
@RequiredArgsConstructor //final이 있는 필드 생성자 생성
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
