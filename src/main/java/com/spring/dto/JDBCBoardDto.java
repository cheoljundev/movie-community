package com.spring.dto;

import com.spring.dao.board.Post;
import com.spring.service.MemberService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCBoardDto implements BoardDto {

    private final JdbcTemplate template;
    private final MemberService memberService;

    public JDBCBoardDto(DataSource dataSource, MemberService memberService) {
        template = new JdbcTemplate(dataSource);
        this.memberService = memberService;
    }

    @Override
    public Post save(Post post) {
        String query = "INSERT INTO " + TABLE_NAME + " (" + ID_COLUMN + ", " + TITLE_COLUMN + ", " + CONTENT_COLUMN + ", " + WRITER_COLUMN + ", " + STORE_FILE_NAME_COLUMN + ", " + FILE_NAME_COLUMN + ") VALUES (SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?)";
        String title = post.getTitle();
        String content = post.getContent();
        Long writer = post.getWriter().getId();
        String storeFileName = post.getStoreFileName();
        String fileName = post.getFileName();

        template.update(query, title, content, writer, storeFileName, fileName);


        return findAll().get(0);
    }

    @Override
    public Post remove(Integer id) {
        Post removedPost = findById(id);
        String query = "DELETE FROM BOARD WHERE ID = ?";
        template.update(query, id);
        return removedPost;
    }

    @Override
    public Post findById(Integer id) {
        String query = "SELECT * FROM BOARD WHERE id = ?";
        return template.queryForObject(query, postRowMapper(), id);
    }

    @Override
    public List<Post> findAll() {
        String query = "SELECT * FROM BOARD ORDER BY ID DESC";
        return template.queryForObject(query, postListRowMapper());
    }

    private RowMapper<List<Post>> postListRowMapper() {
        return (((rs, rowNum) -> {
            List<Post> list = new ArrayList<>();
            do {
                Post post = new Post();
                post.setId(rs.getInt(ID_COLUMN));
                post.setTitle(rs.getString(TITLE_COLUMN));
                post.setContent(rs.getString(CONTENT_COLUMN));
                post.setWriter(memberService.findById(rs.getLong(WRITER_COLUMN)));
                post.setDate(rs.getDate(DATE_COLUMN));
                post.setStoreFileName(rs.getString(STORE_FILE_NAME_COLUMN));
                post.setFileName(rs.getString(FILE_NAME_COLUMN));
                list.add(post);
            } while (rs.next());
            return list;
        }));
    }

    @Override
    public int pages(int maxView) {
        String query = "select count(*) AS posts from board";
        int posts = template.queryForObject(query, Integer.class);
        return (int) Math.ceil((double) posts / maxView);
    }

    private RowMapper<Post> postRowMapper() {
        return (((rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getInt(ID_COLUMN));
            post.setTitle(rs.getString(TITLE_COLUMN));
            post.setContent(rs.getString(CONTENT_COLUMN));
            post.setWriter(memberService.findById(rs.getLong(WRITER_COLUMN)));
            post.setDate(rs.getDate(DATE_COLUMN));
            post.setStoreFileName(rs.getString(STORE_FILE_NAME_COLUMN));
            post.setFileName(rs.getString(FILE_NAME_COLUMN));
            return post;
        }));
    }
}
