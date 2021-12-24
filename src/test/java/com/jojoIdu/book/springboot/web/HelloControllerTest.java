package com.jojoIdu.book.springboot.web;

import com.jojoIdu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


//Test 진행시 JUnit에 내장된 실행자 외에 다른실행자 실행
//스프링 부트 테스트와 JUnit 사이의 연결자 역할
@RunWith(SpringRunner.class)

//여러 스프링 테스트 어노테이션중 Web(Spring MVC)에 집중할 수 있는 어노테이션
//선언시 @Controller, @ControllerAdvice 사용 가능
//@Service, @Component, @Repository등은 사용 불가
/*@WebMvcTest(controllers = HelloController.class)*/

/*security관련 테스트에는 @Controller, @ControllerAdvice를 제외한
* 나머지 @repository, @Service등은 스캔대상이아니기 때문에
* SecurityConfig는 읽지만 CustomOAuth2UserService를 읽을 수없어 에러발생
* 이에 스캔 대상에서 SecurityConfig 제거*/
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;//스프링 MVC 테스트 시작점, HTTP GET, POST 등 API TEST 가능

    //Spring security 적용 관련
    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }


    //Spring security 적용 관련
    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다()throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                            //param: API TEST시 요청 파라미터 설정, 단 String만, 나머지 문자열로 변경
                            .param("name", name)
                            .param("amount",String.valueOf(amount)))
                            .andExpect(status().isOk())
                            //jsonpath : JSON 응답값을 필드별로 검증 할수 있는 메서드
                            //$를 기준으로 필드명을 명시
                            .andExpect(jsonPath("$.name",is(name)))
                            .andExpect(jsonPath("$.amount",is(amount)));
    }


 /* 일반 테스트
  @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다()throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        //param: API TEST시 요청 파라미터 설정, 단 String만, 나머지 문자열로 변경
                        .param("name", name)
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                //jsonpath : JSON 응답값을 필드별로 검증 할수 있는 메서드
                //$를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }*/
}