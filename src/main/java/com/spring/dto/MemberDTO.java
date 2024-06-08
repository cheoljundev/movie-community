package com.spring.dto;

import com.spring.dao.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDTO {
    Member save(Member member);
    Member findById(Long id);
    Optional<Member> findByUserId(String userid);
    List<Member> findAll();
}
