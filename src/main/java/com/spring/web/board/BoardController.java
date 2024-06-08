package com.spring.web.board;

import com.spring.dao.board.Post;
import com.spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String list(@RequestParam(value = "page", required = false) Integer page, Model model) {

        int pages = boardService.pages();

        List<Post> posts = boardService.findView(page);

        model.addAttribute("posts", posts);
        model.addAttribute("pages", pages);

        return "/board/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, @ModelAttribute Post post){
        Post findPost = boardService.findById(id);
        post.setId(findPost.getId());
        post.setTitle(findPost.getTitle());
        post.setWriter(findPost.getWriter());
        post.setDate(findPost.getDate());
        post.setContent(findPost.getContent());
        return "/board/detail";
    }
}
