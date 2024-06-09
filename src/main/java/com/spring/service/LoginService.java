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
        return memberDTO.findByUserId(loginId).filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
