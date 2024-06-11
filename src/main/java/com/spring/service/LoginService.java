package com.spring.service;

import com.spring.dao.member.Member;
import com.spring.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberDto memberDTO;

    public Member login(String loginId, String password) {
        Member loginMember = memberDTO.findByUserId(loginId);
        if (loginMember.getPassword().equals(password)) {
            return loginMember;
        }
        return null;
    }
}
