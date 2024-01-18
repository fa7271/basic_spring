package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@Controller // 싱글톤으로 만들어준다. == 하나의 객체

// 싱글톤 컴포넌트로 생성 된다. 스프링 빈으로 등록된다.
// " 스프링 빈 "이란 스프링이 생성하고 관리하는 객체를 의미
// 제어의역전 (Inversion Of Control) ioc컨테이너가 스프링링빈을 관리 (빈을 생성, 의존성 주입)
// @RequiredArgsConstructor 주입3번
public class MemberController {

//    @Autowired // 의존성 주입 1 (DI) , 필드 주입
//    private MemberService memberService;

//    의존성 밥법 2 > 가장 많이 사용
//    생성자 주입
//    장점 final을 통해 상수로 사용가능, 다형성 구현 가능, 순화참조방지


    private final MemberService memberService;

    //    @생성자가 1개밖에 없을때에는 Autowired 생략가룝
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //    의존성 주입 방법 3 @RequiredArgsConstructor 을 이용한 방식
//    @RequiredArgsConstructor : @NonNull 붙어있는 필드 또는 초기화되지 않은 final 필드를 대상으로 생성자 생성
//    private final MemberService memberService;
    @GetMapping("members/create")
    public String memberCreateScreen() {
        return "member/member-create-screen";
    }

    @PostMapping("members/create")
//    @ResponseBody
    public String memberCreate(MemberRequestDto memberRequestDto) {

//        transaction 및 에러 처리

//        try {
//            memberService.memberCreate(memberRequestDto);
//            return "redirect:/members";
//        }catch (IllegalArgumentException e){
//            return "404-error-page";
//
//        }
        memberService.memberCreate(memberRequestDto);
        return "redirect:/members";

//        url 리다이렉트 /를 안 하면 상대경로로 간다. / 해주기
    }

    @GetMapping("members")
    public String members(Model model) {
        model.addAttribute("memberList", memberService.members());
        return "member/member-list";
    }


    @GetMapping("/member/find")
    public String memberFind(Model model, @RequestParam(value = "id") int id) {
        try {
            MemberResponseDto memberResponseDto = memberService.findById(id);
            model.addAttribute("memberList", memberResponseDto);
            return "member/member-detail";
        } catch (EntityNotFoundException e) {
            //            잘못된 페이지 요청시 에러페이지를 띄어줌
            return "404-error-page";
        }
    }

/*

    @GetMapping("/member/find")
    public ResponseEntity<String> memberFind(Model model, @RequestParam(value = "id") int id) {
        try {
            MemberResponseDto memberResponseDto = memberService.findById(id);
            return new ResponseEntity<>(memberResponseDto.toString(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
//            잘못된 페이지 요청시 에러페이지를 띄어줌
            String html = "<h1> 없음 </h1>";
            return new ResponseEntity<>(html, HttpStatus.NOT_FOUND);
        }
    }

*/

    @GetMapping("member/delete")
    public String delete(@RequestParam(value = "id") int id) {


        memberService.delete(id);

        return "redirect:/members";
    }

    @PostMapping("member/update")
    public String update(Model model, MemberRequestDto memberRequestDto) {
        memberService.update(memberRequestDto);
        return "redirect:/member/find?id=" + memberRequestDto.getId();
    }
}
