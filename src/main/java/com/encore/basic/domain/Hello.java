package com.encore.basic.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Data // getter, setter 밑 toString, equals 등 사전 구현
public class Hello {
    private String name;
    private String email;
    private String password;

//    public Hello(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }
}
