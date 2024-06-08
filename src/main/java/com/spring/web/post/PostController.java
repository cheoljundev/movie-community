package com.spring.web.post;

import com.spring.dao.post.Post;
import com.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Integer id, @ModelAttribute Post post){
        Post findPost = postService.findById(id);
        post.setId(findPost.getId());
        post.setTitle(findPost.getTitle());
        post.setWriter(findPost.getWriter());
        post.setDate(findPost.getDate());
        post.setContent(findPost.getContent());
        return "/post/detail";
    }
}
