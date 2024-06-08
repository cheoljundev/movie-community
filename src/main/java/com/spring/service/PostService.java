package com.spring.service;

import com.spring.dao.post.Post;
import com.spring.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDTO postDTO;
    private final int MAX_VIEW = 10;

    public Post save(Post post) {
        return postDTO.save(post);
    }

    public Post remove(Integer id){
        return postDTO.remove(id);
    }

    private List<Post> findAll() {
        return postDTO.findAll();
    }

    public int pages(){
        return postDTO.pages(MAX_VIEW);
    }

    public Post findById(Integer id){
        return postDTO.findById(id);
    }

    public List<Post> findView(Integer page) {
        List<Post> allPosts = findAll();

        if (page == null){
            page = 1;
        }

        int startIndex = (page - 1) * MAX_VIEW + 1;
        int endIndex = page * MAX_VIEW;

        if (allPosts.size() <= endIndex) {
            endIndex = allPosts.size()-1;
        }

        List<Post> viewPosts = new ArrayList<>();

        for (int i = startIndex; i <= endIndex; i++) {
            viewPosts.add(allPosts.get(i-1));
        }

        return viewPosts;

    }
}
