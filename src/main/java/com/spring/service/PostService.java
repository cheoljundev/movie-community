package com.spring.service;

import com.spring.dao.post.Post;
import com.spring.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDTO postDTO;

    public Post save(Post post) {
        return postDTO.save(post);
    }

    public Post remove(Integer id){
        return postDTO.remove(id);
    }

    public List<Post> findAll() {
        return postDTO.findAll();
    }

    public int pages(int maxView){
        return postDTO.pages(maxView);
    }
}
