package com.jojoIdu.book.springboot.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //이 어노테이션이 생성될 수 있는 위치 지정
@Retention(RetentionPolicy.RUNTIME) // *1
public @interface LoginUser {   //LoginUser의 이름을 가진 어노테이션 생성

    //*1
    //어노테이션 타입을 어디까지 보유할지를 설정하는 것
    //RetentionPolicy의 값을 넘겨주는 것으로 어노테이션의 메모리 보유 범위가 결정
    //RetentionPolicy 종류
    // Source : 사실상 어노테이션을 주석처럼 쓰는 것
    // Class :  컴파일러가 컴파일에서는 어노테이션의 메모리를 가져가지만 실질적으로 런타임시에는 사라지게 됨
    //RunTime : JVM이 자바 바이트코드가 담긴 class 파일에서 런타임환경을 구성하고 런타임을 종료할 때까지 메모리는 살아있음
}
