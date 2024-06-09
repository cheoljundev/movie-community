package com.spring.dto;

import com.spring.dao.board.Post;
import com.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.spring.dto.JDBCUtil.getConnection;
import static com.spring.dto.JDBCUtil.rollback;

@Repository
@RequiredArgsConstructor
public class JDBCBoardDto implements BoardDto{

    private final MemberService memberService;

    // 테이블 열
    private static final String TABLE_NAME = "BOARD";
    private static final String ID_COLUMN = "ID";
    private static final String TITLE_COLUMN = "TITLE";
    private static final String CONTENT_COLUMN = "CONTENT";
    private static final String WRITER_COLUMN = "WRITER";
    private static final String DATE_COLUMN = "REG_DATE";
    private static final String STORE_FILE_NAME_COLUMN = "STOREFILENAME";
    private static final String FILE_NAME_COLUMN = "FILENAME";

    @Override
    public Post save(Post post) {
        String query = "INSERT INTO " + TABLE_NAME + " (" + ID_COLUMN + ", " + TITLE_COLUMN + ", " + CONTENT_COLUMN + ", " + WRITER_COLUMN + ", " + STORE_FILE_NAME_COLUMN + ", " + FILE_NAME_COLUMN + ") VALUES (SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setLong(3, post.getWriter().getId());
            pstmt.setString(4, post.getStoreFileName());
            pstmt.setString(5, post.getFileName());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }

        return post;
    }

    @Override
    public Post remove(Integer id) {
        String query = "DELETE FROM BOARD WHERE ID = ?";

        Post post = findById(id);

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }

        return post;
    }

    @Override
    public Post findById(Integer id) {
        String query = "SELECT * FROM BOARD WHERE id = " + id;

        Post post = new Post();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                post.setId(rs.getInt(ID_COLUMN));
                post.setTitle(rs.getString(TITLE_COLUMN));
                post.setContent(rs.getString(CONTENT_COLUMN));
                post.setWriter(memberService.findById(rs.getLong(WRITER_COLUMN)));
                post.setDate(rs.getDate(DATE_COLUMN));
                post.setStoreFileName(rs.getString(STORE_FILE_NAME_COLUMN));
                post.setFileName(rs.getString(FILE_NAME_COLUMN));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }

        return post;
    }

    @Override
    public List<Post> findAll() {
        String query = "SELECT * FROM BOARD";
        List<Post> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt(ID_COLUMN));
                post.setTitle(rs.getString(TITLE_COLUMN));
                post.setContent(rs.getString(CONTENT_COLUMN));
                post.setWriter(memberService.findById(rs.getLong(WRITER_COLUMN)));
                post.setDate(rs.getDate(DATE_COLUMN));
                post.setStoreFileName(rs.getString(STORE_FILE_NAME_COLUMN));
                post.setFileName(rs.getString(FILE_NAME_COLUMN));
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }

        return list;

    }

    @Override
    public int pages(int maxView) {
        return (int) Math.ceil((double)findAll().size() / maxView);
    }
}
