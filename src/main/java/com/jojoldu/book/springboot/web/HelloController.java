package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 60p.
// @RestController : JSON을 반환한는 컨트롤러
// => 예전에 @ResponseBody를 각 메소드마다 선언했던 것을 한 번에 사용할 수 있게 해줌.
@RestController
public class HelloController {
    // @GetMapping : HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌.
    // 예전에 @RequestMapping(method = RequestMethod.GET)으로 사용되었음.
    @GetMapping("/hello")
    public String hello() {
        // "/hello"라고 요청이 오면 문자열 hello를 반환.
        return "hello";
    }
    
    // @RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션.
    // (ex. 외부에서 name이란 이름으로 넘긴 파라미터를 메소드 파라미터 String name 에 저장)
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        // 콘솔에서 시각적으로 확인하려고 찍음.
        System.out.println("name : " + name + ", amount : " + amount);

        return new HelloResponseDto(name, amount);
    }
}
