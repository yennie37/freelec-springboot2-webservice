package com.jojoldu.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 179p. User 의 CRUD 를 위한 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
    // 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메소드
    Optional<User> findByEmail(String email);
}
