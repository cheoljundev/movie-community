package com.spring.service;

import com.spring.dao.member.Member;
import com.spring.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDto memberDTO;

    public Member save(Member member) {
        return memberDTO.save(member);
    }

    public Member findById(Long id){
        return memberDTO.findById(id);
    }

    public Optional<Member> findByUserId(String userId) {
        return memberDTO.findByUserId(userId);
    }
}
