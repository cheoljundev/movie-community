package com.spring.dto;

import com.spring.dao.board.Post;

import java.util.List;

public interface BoardDto {
    String TABLE_NAME = "BOARD";
    String ID_COLUMN = "ID";
    String TITLE_COLUMN = "TITLE";
    String CONTENT_COLUMN = "CONTENT";
    String WRITER_COLUMN = "WRITER";
    String DATE_COLUMN = "REG_DATE";
    String STORE_FILE_NAME_COLUMN = "STOREFILENAME";
    String FILE_NAME_COLUMN = "FILENAME";

    Post save(Post post);
    Post remove(Integer id);

    Post findById(Integer id);

    List<Post> findAll();

    int pages(int maxView);
}
