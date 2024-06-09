package com.spring.dto;

import com.spring.dao.board.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryBoardDto implements BoardDto {

    private static final Map<Integer, Post> store = new ConcurrentHashMap<>();
    Integer seq = 0;

    @Override
    public Post save(Post post) {
        post.setId(++seq);
        post.setDate(new Date());
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public Post remove(Integer id) {
        Post removePost = store.get(id);
        store.remove(id);
        return removePost;
    }

    @Override
    public Post findById(Integer id) {
        return store.get(id);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public int pages(int maxView) {
        return (int) Math.ceil((double)findAll().size() / maxView);
    }
}
