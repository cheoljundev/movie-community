package com.spring.service;

import com.spring.constant.BoardConst;
import com.spring.dao.board.Post;
import com.spring.dao.board.PostForm;
import com.spring.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDto boardDto;

    @Value("${file.dir}")
    String fileDir;

    public Post save(PostForm postForm) throws IOException {
        MultipartFile file = postForm.getFile();
        Post post = new Post();

        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            int extIndex = filename.lastIndexOf(".") + 1;
            String ext = filename.substring(extIndex);
            String storeFileName = uuid + "." + ext;
            post.setFileName(filename);
            post.setStoreFileName(storeFileName);
            String fullPathUrl = fileDir + storeFileName;
            file.transferTo(new File(fullPathUrl));
        }

        post.setTitle(postForm.getTitle());
        post.setWriter(postForm.getWriter());
        post.setContent(postForm.getContent());


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
