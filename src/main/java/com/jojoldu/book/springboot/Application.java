package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 57p.
// @SpringBootApplication : 스프링부트의 자동 설정, 스프링Bean 읽기와 생성을 모두 자동으로 설정
//  => 해당 어노테이션이 있는 위치부터 설정을 읽어가므로 이 클래스는 항상 프로젝트의 최상단에 위치해야함.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //내장 WAS 실행
        // => 내장 WAS 를 사용하므로 톰캣설치가 불필요.
        // 언제어디서나 같은 환경에서 배포할수 있기때문에 SpringBoot에서는 내장 WAS 사용을 권장.
        SpringApplication.run(Application.class, args);
    }
}
