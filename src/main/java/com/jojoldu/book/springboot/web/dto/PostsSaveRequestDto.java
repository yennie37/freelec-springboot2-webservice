package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import javafx.geometry.Pos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 104p. 등록, 수정, 조회 API 만들기
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // View Layer 와 DB Layer 의 역할 분리 철저.
    // Entity 클래스를 Request/Response 클래스로 사용X.
    // 데이터베이스와 맞닿는 핵심클래스.
    // Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경됨.
    public Posts toEntity() {
        System.out.println("========== to Entity() 입니다 ==========");

        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
