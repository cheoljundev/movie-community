package com.spring.web.member;

import com.spring.dao.member.Member;
import com.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("user") Member member) {
        return "member/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("user") Member member, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/joinForm";
        }

        memberService.save(member);
        return "member/joinResult";
    }
}
