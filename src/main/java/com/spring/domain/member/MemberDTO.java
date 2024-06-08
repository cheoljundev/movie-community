package com.spring.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberDTO {
    Member save(Member member);
    Member findById(Long id);
    Optional<Member> findByUserId(String userid);
    List<Member> findAll();
}
