package com.jojoIdu.book.springboot.config.auth;

import com.jojoIdu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity      //Spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()

                //h2-console 화면 사용위해 해당옵션 disable
                .headers().frameOptions().disable()
                    .and()

                        //URL별 권한 관리 설정 옵션 시작
                        .authorizeRequests()
                        //권한관리 대상 지정옵션, URL, HTTP메소드별 관리 가능
                        //"/"등 지정된 URL은 permitAll()으로 전체권한
                        .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                        // "/api/v1/**"주소를 가진 api는 USER 권한
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                        //anyRequest : 설정값 이외 나머지 URL
                        //authenticated() : 인증된 사람(로그인)만 가능
                        .anyRequest().authenticated()
                    .and()
                        //로그아웃 기능 시작
                        .logout()
                            //로그아웃 성공시 "/"로 이동
                            .logoutSuccessUrl("/")
                    .and()
                        //oauth2 로그인 설정 시작
                        .oauth2Login()
                            //oauth2 로그인 이후 사용자 정보 가져올 때 설정
                            .userInfoEndpoint()
                                //소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                                // = 추가로 진행하고자하는 기능 명시
                                .userService(customOAuth2UserService);
    }

}
