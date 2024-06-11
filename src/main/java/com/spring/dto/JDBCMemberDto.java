package com.spring.dto;

import com.spring.dao.member.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Repository
public class JDBCMemberDto implements MemberDto {

    private final JdbcTemplate template;

    public JDBCMemberDto(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        String query = "INSERT INTO " + TABLE_NAME + " (" + ID_COLUMN + ", " + USERID_COLUMN + ", " + PASSWORD_COLUMN + ", " + NAME_COLUMN + ") VALUES (SEQ_USER.NEXTVAL, ?, ?, ?)";

        String id = member.getUserId();
        String password = member.getPassword();
        String name = member.getName();

        template.update(query, id, password, name);

        return findByUserId(member.getUserId());
    }

    @Override
    public Member findById(Long id) {
        Member member = new Member();
        String query = "SELECT * FROM USERS WHERE id = ?";
        return template.queryForObject(query, memberRowMapper(), id);
    }

    public Member findByUserId(String userid) {
        String query = "SELECT * FROM USERS WHERE userid = ?";
        return template.queryForObject(query, memberRowMapper(), userid);
    }

    @Override
    public List<Member> findAll() {
        String query = "SELECT * FROM USERS";
        return template.queryForObject(query, memberListRowMapper());
    }

    private RowMapper<List<Member>> memberListRowMapper() {
        return (rs, rowNum) -> {
            List<Member> memberList = new ArrayList<>();
            do {
                Member member = new Member();
                member.setId(rs.getLong(ID_COLUMN));
                member.setUserId(rs.getString(USERID_COLUMN));
                member.setPassword(rs.getString(PASSWORD_COLUMN));
                member.setName(rs.getString(NAME_COLUMN));
                memberList.add(member);
            } while (rs.next());
            return memberList;
        };
    }


    private RowMapper<Member> memberRowMapper() {
        return ((rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong(ID_COLUMN));
            member.setUserId(rs.getString(USERID_COLUMN));
            member.setPassword(rs.getString(PASSWORD_COLUMN));
            member.setName(rs.getString(NAME_COLUMN));
            return member;
        });
    }
}
