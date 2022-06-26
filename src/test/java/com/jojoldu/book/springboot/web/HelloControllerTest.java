package com.jojoldu.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//자동임포트 안되서 수기로 작성함
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

// @RunWith 어노테이션은 Junit5부터 @ExtendWith로 변경.
// @ExtendWith : 테스트 실행시 JUnit에 내장된 실행자 외에 다른 실행자를 실행.
//  여기서는 SpringExtension 이라는 스프링 실행자를 사용.
//  즉, 스프링부트 테스트와 JUnit 사이에 연결자 역할.
//  JUnit5에서 반복적으로 실행되는 클래스나 메서드에 사용.
//  괄호 안에 인자로 확장할 Extension을 추가하여 사용한다.
//  SpringExtension은 Spring TextContext를 사용할 수 있도록 해준다.

// @WebMvcTest : 여러 스프링 테스트 어노테이션 중, Web에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller,@ControllerAdvice 등을 사용할 수 있음.
// 단, @Service, @Component, @Repository등은 사용할 수 없음.
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    // @Autowired : 스프링이 관리하는 Bean 주입 받음.
    @Autowired
    // MockMvc : 웹 애플리케이션을 애플리케이션 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있는 클래스.
    // 웹 API를 테스트 할 때 사용. 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 "/hello" 주소로 HTTP GET 요청. 체이닝(Chaining)이 지원(여러 검증 기능을 이어서 선언).
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                // andExpect(MockMvcResultMatchers) : mvc.perform의 결과를 검증
                // status() : HTTP Header의 Status를 검증
                // isOk() => 200인지 아닌지 검증
                .andExpect(MockMvcResultMatchers.status().isOk())
                // content() : 응답 본문의 내용을 검증
                .andExpect(MockMvcResultMatchers.content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                // param() : API 테스트할 때 사용될 요청 파라미터를 설정. String만 허용.
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // jsonPath() : JSON 응답값을 필드별로 검증할 수 있는 메소드. $ 를 기준으로 필드명 명시.
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount))
                );

    }
}
