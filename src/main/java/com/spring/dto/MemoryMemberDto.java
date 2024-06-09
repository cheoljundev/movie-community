package com.spring.dto;

import com.spring.dao.member.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMemberDto implements MemberDto {

    public static final Map<Long, Member> store = new HashMap<>();
    Long seq = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++seq);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByUserId(String userid) {
        return findAll().stream()
                .filter(member -> member.getUserId().equals(userid))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
