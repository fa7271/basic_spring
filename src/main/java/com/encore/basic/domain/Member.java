package com.encore.basic.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

// DB 에 들어갈 정보, 근데 id, localtime을 직접 넣어주지 않음.
// 즉 Setter가 들어가면 어려워짐 >> 즉 setter빼고, 입력 받기용 DTO 필요
@Getter
//@AllArgsConstructor // 모든 매개변수를 넣은 생성자
@Entity
@ToString
@NoArgsConstructor // reflections 기술을 통해 접근을 할 수 있는데, 깡통 객체가 있어야지 접근이 가능하다.
public class Member {
    @Setter
    @Id // pk 설정
    // identity = auto_increment설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement 설정
    private int id;
    //    String 은 Db의 VarChar로 변환

    private String name;
    @Column(nullable = false, length = 50)
    private String email;
    @Setter
    private String password;
    @Setter
    @Column(name = "created_time") // name 옵션을 통해, DB의 컬럼명 별도 지정가능
    @CreationTimestamp
    private LocalDateTime create_time;

    @UpdateTimestamp
    private LocalDateTime updated_time;


    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updateMember(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

//    AllArgsConstructor
//    public Member(int id, String name, String email, String password, LocalDateTime localDateTime) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.localDateTime = localDateTime;
//    }

