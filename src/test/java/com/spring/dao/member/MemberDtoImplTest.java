package com.spring.dao.member;

import com.spring.dto.MemberDto;
import com.spring.dto.MemoryMemberDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDtoImplTest {
    MemberDto memberDTO = new MemoryMemberDto();

    @AfterEach
    void afterEach() {
        MemoryMemberDto.store.clear();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setUserId("testuser");
        member.setPassword("1234");
        member.setName("tester");

        Member savedMember = memberDTO.save(member);

        assertThat(member.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    void findById() {
        Member member = new Member();
        member.setUserId("testuser");
        member.setPassword("1234");
        member.setName("tester");

        memberDTO.save(member);

        Member findMember = memberDTO.findById(1L);

        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findByUserId() {
        Member member = new Member();
        member.setUserId("testuser");
        member.setPassword("1234");
        member.setName("tester");

        memberDTO.save(member);

        Member findUser = memberDTO.findByUserId("testuser").get();

        assertThat(member).isEqualTo(findUser);
    }
}