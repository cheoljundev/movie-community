package com.spring.service;

import com.spring.constant.BoardConst;
import com.spring.dao.board.Post;
import com.spring.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDto boardDto;

    public Post save(Post post) {
        return boardDto.save(post);
    }

    public Post remove(Integer id){
        return boardDto.remove(id);
    }

    private List<Post> findAll() {
        return boardDto.findAll();
    }

    public int pages(){
        return boardDto.pages(BoardConst.MAX_VIEW);
    }

    public Post findById(Integer id){
        return boardDto.findById(id);
    }

    public List<Post> findView(Integer page) {
        List<Post> allPosts = findAll();

        if (page == null){
            page = 1;
        }

        int startIndex = (page - 1) * BoardConst.MAX_VIEW + 1;
        int endIndex = page * BoardConst.MAX_VIEW;

        if (allPosts.size() <= endIndex) {
            endIndex = allPosts.size();
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
        int result = minPageLimit + BoardConst.PAGE_SIZE - 1;
        int pages = pages();

        if (result > pages){
            return pages;
        }

        return result;
    }
}
