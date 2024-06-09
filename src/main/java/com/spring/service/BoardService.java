package com.spring.service;

import com.spring.dao.board.Post;
import com.spring.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDto boardDTO;
    private final int MAX_VIEW = 10;
    public static final int PAGE_SIZE = 10;

    public Post save(Post post) {
        return boardDTO.save(post);
    }

    public Post remove(Integer id){
        return boardDTO.remove(id);
    }

    private List<Post> findAll() {
        return boardDTO.findAll();
    }

    public int pages(){
        return boardDTO.pages(MAX_VIEW);
    }

    public Post findById(Integer id){
        return boardDTO.findById(id);
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

    public int minPageLimit(Integer page){
        return ((page - 1) / 10) * 10 + 1;
    }

    public int maxPageLimit(int minPageLimit){
        return minPageLimit + PAGE_SIZE - 1;
    }
}
