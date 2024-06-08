package com.spring;

import com.spring.dao.member.Member;
import com.spring.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class testCase {
    private final MemberService memberService;

    @PostConstruct
    void init() {
        Member member = new Member();
        member.setUserId("test");
        member.setName("tester");
        member.setPassword("test");
        memberService.save(member);
    }
}
