package com.jojoIdu.book.springboot.config.auth.dto;

import com.jojoIdu.book.springboot.domain.user.Users;
import lombok.Getter;

@Getter
public class SessionUser {      //인증된 사용자 정보만 필요
    private String name;
    private String email;
    private String picture;

    public SessionUser(Users user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
