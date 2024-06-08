package com.spring.service;

import com.spring.domain.member.Member;
import com.spring.dto.MemberDTO;
import com.spring.dto.MemberDTOImpl;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.bcel.generic.ANEWARRAY;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDTO memberDTO;

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
