package com.spring.web.board;

import com.spring.dao.board.Post;
import com.spring.dao.board.PostForm;
import com.spring.dao.member.Member;
import com.spring.service.BoardService;
import com.spring.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Value("${file.dir}")
    String fileDir;

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

        if (page == 1) {
            return "/board/list";
        }else if (page > pages) {
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
        post.setStoreFileName(findPost.getStoreFileName());
        return "/board/detail";
    }

    @GetMapping("/write")
    public String writeForm(@ModelAttribute("post") Post post) {
        return "/board/writeForm";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, @ModelAttribute(name = "post") PostForm postForm) throws IOException {
        postForm.setWriter(loginMember);
        boardService.save(postForm);
        return "redirect:/board";
    }

    // @GetMapping("/image/{fileName}")
    @ResponseBody
    public Resource downloadImage(@PathVariable("fileName") String fineName) throws MalformedURLException {
        return new UrlResource("file:" + fileDir + fineName);
    }


    @GetMapping("/image/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> downloadImageV2(@PathVariable("fileName") String fileName) throws MalformedURLException {
        Resource resource = new UrlResource("file:" + fileDir + fileName);

        // 파일 확장자를 통해 MIME 타입을 결정
        String mimeType;
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (ext) {
            case "jpg":
            case "jpeg":
                mimeType = "image/jpeg";
                break;
            case "png":
                mimeType = "image/png";
                break;
            case "gif":
                mimeType = "image/gif";
                break;
            default:
                mimeType = "application/octet-stream";  // 일반적인 바이너리 파일
        }

        // Content-Type 헤더를 설정하고 파일을 반환
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, mimeType);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
