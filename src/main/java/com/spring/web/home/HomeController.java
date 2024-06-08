package com.spring.web.home;

import com.spring.dao.post.Post;
import com.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String index(@RequestParam(value = "page", required = false) Integer page, Model model) {

        if (page == null){
            page = 1;
        }

        List<Post> posts = postService.findAll();
        final int MAX_VIEW = 10;
        int pages = postService.pages(MAX_VIEW);

        model.addAttribute("posts", posts);
        model.addAttribute("pages", pages);

        int startIndex = (page - 1) * MAX_VIEW + 1;
        int endIndex = page * MAX_VIEW;

        if (posts.size() <= endIndex) {
            endIndex = posts.size()-1;
        }

        model.addAttribute("startIndex", startIndex);
        model.addAttribute("endIndex", endIndex);

        log.info("posts ={}", posts);
        log.info("start={}, end={}", startIndex, endIndex);

        return "index";
    }
}
