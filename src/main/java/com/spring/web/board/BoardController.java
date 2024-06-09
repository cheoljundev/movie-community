package com.spring.web.board;

import com.spring.dao.board.Post;
import com.spring.dao.member.Member;
import com.spring.service.BoardService;
import com.spring.web.SessionConst;
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

        if (page == null) {
            page = 1;
        }

        List<Post> posts = boardService.findView(page);
        int pages = boardService.pages();
        int min = boardService.minPageLimit(page);
        int max = boardService.maxPageLimit(min);

        model.addAttribute("posts", posts);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("minPage", min);
        model.addAttribute("maxPage", max);

        if (page > pages) {
            return "redirect:/";
        }

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

    @GetMapping("/write")
    public String writeForm(@ModelAttribute("post") Post post) {
        return "/board/writeForm";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, @ModelAttribute(name = "post") Post post) {
        post.setWriter(loginMember);
        boardService.save(post);
        return "redirect:/board/" + post.getId();
    }
}
