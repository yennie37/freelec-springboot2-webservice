package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 104p. 등록, 수정, 조회 API 만들기
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        System.out.println("========== PostsService save() 입니다 ==========");

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        System.out.println("========== PostsService update() 입니다 ==========");

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다... id=" + id));

        return new PostsResponseDto(entity);
    }

    // 148p.
    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하지만 조회기능만 남겨두어 조회 속도 개선(사용추천)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                // == .map(posts -> new PostsListResponseDto(posts))
                // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해
                // PostsListResponseDto 변환 -> List로 반환하는 메소드.
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 159p.
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        // JpaRepository 에서 이미 가지고 있는 delete 메소드.
        // 엔티티를 파라미터로 받아 삭제도 가능하고, deleteById 메소드를 활용하여 id로 삭제도 가능.
        // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 삭제.
        postsRepository.delete(posts);
    }
}
