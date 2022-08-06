package com.jojoldu.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 178p. 각 사용자의 권한을 관리할 Enum 클래스
@Getter
@RequiredArgsConstructor
public enum Role {
    // 스프링 시큐리티에서는 권한코드에 항상 ROLE_ 가 앞에 있어야함.
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
