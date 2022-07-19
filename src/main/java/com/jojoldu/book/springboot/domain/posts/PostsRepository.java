package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 94p. Posts 클래스로 Database를 접근하게 해줄 JpaRepository
// ibatis MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자.
// JPA 에선 Repository 라고 부르며 인터페이스로 생성.
// 단순히 인터페이스를 생성한 후 JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동 생성됨.
// Entity 클래스와 기본 Entity Repository는 함께 위치해야 함.
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // 147p. Repository 코드 작성
    // SpringDataJpa에서 제공되지 않는 메소드는 아래와 같이 쿼리로 작성 가능.
    // - 규모가 있는 프로젝트의 데이터 조회는 Entity 클래스만으로 처리가 어려워 조회용 프레임워크를 추가로 사용.
    //   ex) querydsl(저자추천), jooq, MyBatis 등.
    //   but 등록/수정/삭제 등은 SpringDataJpa 를 통해 진행.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
