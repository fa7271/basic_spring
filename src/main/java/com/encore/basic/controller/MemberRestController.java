package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
@Api(tags ="회원관리서비스")
public class MemberRestController {
    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("members/create")
//    프론트에서 데이터 넘겨줌, REST에서는 {}에 담아서 넣어줌
    public String memberCreate(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.memberCreate(memberRequestDto);
        return "ok";
    }

    @GetMapping("members")
    public List<MemberResponseDto> members() {
        return memberService.members();
    }

    @GetMapping("/member/find/{id}")
    public ResponseEntity<Map<String, Object>> memberFind(@PathVariable int id) {
        MemberResponseDto memberResponseDto = null;
        try {
            memberResponseDto = memberService.findById(id);
            return ResponseEntityController.ResponseMessage(HttpStatus.OK, memberResponseDto);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntityController.errResponseMessage(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("member/delete/{id}")
    public String delete(@PathVariable int id) {
        memberService.delete(id);
        return "ok";
    }

    @PatchMapping("member/update") // 파일 덮어쓰기
    public MemberResponseDto update(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.update(memberRequestDto);

        return memberService.findById(memberRequestDto.getId());
    }
}
