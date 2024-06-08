package com.spring.dao.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Member {
    private Long id;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
