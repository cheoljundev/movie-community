package com.spring.web.member;

import com.spring.domain.member.Member;
import com.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("user") Member member) {
        return "join";
    }

    @PostMapping("/join")
    @ResponseBody
    public String join(@ModelAttribute("user") Member member) {
        memberService.save(member);
        return "OK";
    }
}
