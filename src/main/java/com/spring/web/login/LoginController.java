package com.spring.web.login;

import com.spring.dao.member.Member;
import com.spring.service.LoginService;
import com.spring.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") Member member) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Member member, Model model, HttpServletRequest request) {
        Member loginMember = loginService.login(member.getUserId(), member.getPassword());

        if (loginMember ==  null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();

        return "redirect:/";
    }
}
