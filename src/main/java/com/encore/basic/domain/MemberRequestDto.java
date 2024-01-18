package com.encore.basic.domain;


import lombok.Data;

// 받을 것만 가져옴
@Data
public class MemberRequestDto {
    private int id;
    private String name;
    private String email;
    private String password;
}
