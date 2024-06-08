package com.spring;

import com.spring.dao.member.Member;
import com.spring.dao.board.Post;
import com.spring.service.MemberService;
import com.spring.service.BoardService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class testCase {
    private final MemberService memberService;
    private final BoardService boardService;

    @PostConstruct
    void init() {
        Member member = new Member();
        member.setUserId("test");
        member.setName("tester");
        member.setPassword("test");
        memberService.save(member);


        for (int i = 0; i < 300; i++) {
            Post post = new Post();
            post.setTitle("테스트글");
            post.setWriter(member);
            post.setContent("test 글내용");
            boardService.save(post);
        }
    }
}
