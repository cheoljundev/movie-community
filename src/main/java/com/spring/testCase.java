package com.spring;

import com.spring.dao.member.Member;
import com.spring.dao.post.Post;
import com.spring.service.MemberService;
import com.spring.service.PostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class testCase {
    private final MemberService memberService;
    private final PostService postService;

    @PostConstruct
    void init() {
        Member member = new Member();
        member.setUserId("test");
        member.setName("tester");
        member.setPassword("test");
        memberService.save(member);

        Post post = new Post();
        post.setTitle("테스트글");
        post.setWriter(member);
        post.setContent("test 글내용");
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
        postService.save(post);
    }
}
