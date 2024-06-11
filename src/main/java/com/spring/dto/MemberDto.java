package com.spring.dto;

import com.spring.dao.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDto {
    String TABLE_NAME = "USERS";
    String ID_COLUMN = "ID";
    String USERID_COLUMN = "USERID";
    String PASSWORD_COLUMN = "PASSWORD";
    String NAME_COLUMN = "NAME";

    Member save(Member member);
    Member findById(Long id);
    Member findByUserId(String userid);
    List<Member> findAll();
}
