package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.SpringDataJpaMemberRepository;
import com.encore.basic.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {

    //    @ResponseStatus 어노테이션 방식: 가장 심플한 방식

    //    200 vs 201(created, 생성됐다.)
    @GetMapping("responsestatus")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus() {
        return "OK";
    }

    @GetMapping("responsestatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2() {
        Member member = new Member("song", "123", "1234");
        return member;
    }

    @GetMapping("custom1")
    public ResponseEntity<Member> custom11() {
        Member member = new Member("song", "123", "12354");
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    //    ResponseEntity<String> 일 경우 text/html로 설정
//    활용도 떨어짐
    @GetMapping("custom2")
    public ResponseEntity<String> custom2() {
        String html = "<h1>없는 ID 입니다. </h1>";
//        Member member = new Member("song", "123", "12354");
        return new ResponseEntity<>(html, HttpStatus.NOT_FOUND); // 상태코드만 넣으면 상태코드만 나감.
    }

    //    map 형태의 메시지 커스텀 >> 현직에서 많이 보임
    // 실패
    public static ResponseEntity<Map<String, Object>> errResponseMessage(HttpStatus status, String Message) {
        HashMap<String, Object> body = new HashMap<>();
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        body.put("status", Integer.toString(status.value()));
        body.put("error message", Message);

        return new ResponseEntity<>(body, status);
    }

    // 성공
    public static ResponseEntity<Map<String, Object>> ResponseMessage(HttpStatus status, MemberResponseDto memberResponseDto) {
        HashMap<String, Object> body = new HashMap<>();
//        HttpStatus status = HttpStatus.CREATED;
        body.put("status", Integer.toString(status.value()));
//        Member member = new Member("송보석", "송보석1", "송보석2");
        body.put("message", memberResponseDto);
        return new ResponseEntity<>(body, status);
    }

    //    메서드 체이닝 방식 : ResponseEntity 클래스 메서드 사용
    @GetMapping("chaing1")
    public ResponseEntity<Member> chaing() {
        Member member = new Member("송보석tjr", "송보석tjr1", "송보석tjr2");
        return ResponseEntity.ok(member);
    }

    @GetMapping("chaing2")
    public ResponseEntity<Member> chaing2() {
        return ResponseEntity.notFound().build();
    }
    @GetMapping("chaing3")
    public ResponseEntity<Member> chaing3() {
        Member member = new Member("송보석tjr", "송보석tjr1", "송보석tjr2");

        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
