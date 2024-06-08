package com.spring.dao.member;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String userId;
    private String name;
    private String password;
}
