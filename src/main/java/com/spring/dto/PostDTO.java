package com.spring.dto;

import com.spring.dao.post.Post;

import java.util.List;

public interface PostDTO {
    Post save(Post post);
    Post remove(Integer id);

    Post findById(Integer id);

    List<Post> findAll();

    int pages(int maxView);
}
