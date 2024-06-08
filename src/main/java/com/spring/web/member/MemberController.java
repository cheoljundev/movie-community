package com.spring.web.member;

import com.spring.dao.member.Member;
import com.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("user") Member member) {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("user") Member member) {
        memberService.save(member);
        return "member/joinResult";
    }
}
