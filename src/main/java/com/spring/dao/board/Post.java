package com.spring.dao.board;

import com.spring.dao.member.Member;
import lombok.Data;

import java.util.Date;

@Data
public class Post {
    Integer id;
    String title;
    String content;
    Member writer;
    Date date;
}
