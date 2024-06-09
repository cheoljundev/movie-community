package com.spring.dao.board;

import com.spring.dao.member.Member;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class Post {
    private Integer id;
    private String title;
    private String content;
    private Member writer;
    private Date date;
    private String storeFileName;
    private String fileName;
}
