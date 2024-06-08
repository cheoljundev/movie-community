package com.spring.web.home;

import com.spring.dao.post.Post;
import com.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        int pages = postService.pages();

        List<Post> posts = postService.findView(page);

        model.addAttribute("posts", posts);
        model.addAttribute("pages", pages);

        return "index";
    }
}
