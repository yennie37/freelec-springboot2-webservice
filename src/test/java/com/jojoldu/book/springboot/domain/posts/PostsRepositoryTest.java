package com.jojoldu.book.springboot.domain.posts;

// 교재에서는 @After을 사용했으나 내가 진행중인 버전에선 @AfterEach로 대체
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat; //수동 import함

import java.time.LocalDateTime;
import java.util.List;

// 95p.
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    // @AfterEach : JUnit 에서 단위테스트가 끝날 때마다 수행되는 메소드를 지정.
    // 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용.
    // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아있어 다음 테스트 실행 시 테스트가 실패할 수 있음.
    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    //@Test
    public void 게시글저장_불러오기() {
        //given
        String title = "TEST 게시글 제목";
        String content = "TEST 본문 !!!!";

        // postsRepository.save() : 테이블 posts에 insert/update 쿼리 실행.
        // id 값이 있다면 update, 없다면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("testmail222@gmail.com")
                .build()
        );

        //when
        //postsRepository.findAll() : 테이블 posts에 있는 모든 데이터를 조회해오는 메소드.
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    // 122p.
    //@Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        //결과 : >>>>> createDate = 2022-07-17T17:10:38.222, modifiedDate = 2022-07-17T17:10:38.222
        System.out.println(">>>>> createDate = " + posts.getCreatedDate()
                + ", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
