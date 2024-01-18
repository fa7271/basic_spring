package com.encore.basic.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

// 받을 것만 가져옴
@Data
public class MemberResponseDto { // 직렬화 할때 순환참조.
//    사용자가 ID 값, localdate값은 굳이 알 필요가 없음
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime create_time;
}
