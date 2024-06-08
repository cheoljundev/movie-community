package com.spring.domain.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemberDTOImpl implements MemberDTO{

    public static final Map<Long, Member> store = new ConcurrentHashMap<>();
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
