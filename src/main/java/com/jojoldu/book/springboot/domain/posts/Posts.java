package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 88p.
// @Getter : 클래스 내 모든 필드의 Getter 메소드를 자동 생성.
// @NoArgsConstructor : 기본 생성자 자동 추가. public Posts() {}와 같은 효과.
// @Entity : 테이블과 링크될 클래스임 명시. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭.
@Getter
@NoArgsConstructor
@Entity
public class Posts {
    // @Id : 해당 테이블의 PK 필드 명시.
    // @GeneratedValue : PK 생성 규칙 명시
    // + 웬만하면 Entity의 PK는 Long 타입의 Auto_increment 추천.
    //   주민등록번호, 복합키 등은 유니크 키로 별도로 추가하는 것 추천.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // @Column : 테이블의 칼럼 명시. 굳이 하지않아도 해당 클래스의 필드는 모두 칼럼이 됨.
    // 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용. 문자열 기본값 VARCHAR(255)
    @Column(length = 500, nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    private String author;

    // @Builder : 해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 저자는 주요 어노테이션일수록 클래스에 가깝게 선언(후에 쉽게 제거 등).
    // @Entity 는 JPA의 어노테이션
    // @Getter, @NoArgsConstructor 은 롬복의 어노테이션 - 필수X

    // Entity 클래스에서는 Setter 메소드를 만들지 않고, 생성자를 통해 최종값을 채운 후 DB에 삽입.
    // 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경.
    // @Builder를 통해 제공되는 빌더 클래스 사용.
}
