package com.spring.dto;

import com.spring.dao.member.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.spring.dto.JDBCUtil.getConnection;
import static com.spring.dto.JDBCUtil.rollback;

@Repository
public class JDBCMemberDto implements MemberDto{

    // 테이블 열
    private static final String TABLE_NAME = "USERS";
    private static final String ID_COLUMN = "ID";
    private static final String USERID_COLUMN = "USERID";
    private static final String PASSWORD_COLUMN = "PASSWORD";
    private static final String NAME_COLUMN = "NAME";

    @Override
    public Member save(Member member) {
        String query = "INSERT INTO " + TABLE_NAME + " (" + ID_COLUMN + ", " + USERID_COLUMN + ", " + PASSWORD_COLUMN + ", " + NAME_COLUMN + ") VALUES (SEQ_USER.NEXTVAL, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);
            pstmt.setString(1, member.getUserId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }

        return findByUserId(member.getUserId()).get();
    }

    @Override
    public Member findById(Long id) {
        Member member = new Member();
        String query = "SELECT * FROM USERS WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                member.setId(rs.getLong(ID_COLUMN));
                member.setUserId(rs.getString(USERID_COLUMN));
                member.setPassword(rs.getString(PASSWORD_COLUMN));
                member.setName(rs.getString(NAME_COLUMN));
            } else {
                throw new NoSuchElementException("유저를 찾을 수 없습니다. memberId=" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
        return member;
    }

    @Override
    public Optional<Member> findByUserId(String userid) {
        return findAll().stream()
                .filter(member -> member.getUserId().equals(userid))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {

        List<Member> list = new ArrayList<>();

        String query = "SELECT * FROM USERS";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong(ID_COLUMN));
                member.setUserId(rs.getString(USERID_COLUMN));
                member.setPassword(rs.getString(PASSWORD_COLUMN));
                member.setName(rs.getString(NAME_COLUMN));
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
        return list;
    }

}
