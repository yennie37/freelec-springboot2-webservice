package com.jojoldu.book.springboot.web.dto;

import org.junit.jupiter.api.Test;
//import org.junit.Test; // 교재의 임포트 패키지명인데 내가 사용하는 버전에선 위의 패키지를 사용해야함.
import static org.assertj.core.api.Assertions.assertThat; // 자동으로 임포트가 안되서 따로 복사해서 붙임.

// 73p.
public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        // AssertJ : 자바 JUnit의 테스트코드에 사용되어, 테스트코드의 가독성과 편의성을 높여주는 라이브러리. 체이닝 지원.
        // assertThat(검증대상) : assertj라는 테스트 검증 라이브러리의 검증 메소드.
        // isEqualTo: assertj의 동등 비교 메소드. assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        // ※ 여기서 Junit이 아닌 assertj의 assertThat를 사용한 이유
        // 1. Junit의 assertThat을 쓰게되면 is()와 같이 CoreMatcherts 라이브러리가 필요하지만,
        //    assertj의 assertThat은 추가적인 라이브러리가 필요하지 않음
        // 2. 자동완성이 좀 더 확실하게 지원.
    }
}
