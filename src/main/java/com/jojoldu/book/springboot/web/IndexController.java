package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


// 150p. Controller 변경
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("========== IndexController index() 입니다 ==========");
        model.addAttribute("posts", postsService.findAllDesc());

        // 190p. CustomOAuth2UserService에서 로그인 성공시 세션에 SessionUser를 저장하도록 구현함.
        // => 로그인 성공시 httpSession.getAttribute("user") 로 값을 가져올 수 있음.
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            System.out.println("└ IndexController index() - user is not null. Get userName.");
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }
    /*
    @GetMapping("/posts/save")
    public String postsSave() {
        System.out.println("========== IndexController postsSave() 입니다 ==========");

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        System.out.println("========== IndexController postsUpdate() 입니다 ==========");

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
    */
}

// 132p. 머스테치에 URL 매핑
/*
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        System.out.println("========== postsSave() 입니다 ==========");
        return "posts-save";
    }
}
*/









