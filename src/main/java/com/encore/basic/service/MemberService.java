package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
//transactional 클래스 단위로 붙이면 모든 메서드에 각각 transactional 적용
//@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.memberRepository = springDataJpaMemberRepository;
    }


    public List<MemberResponseDto> members() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for (Member m : members) {

            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(m.getId());
            memberResponseDto.setName(m.getName());
            memberResponseDto.setEmail(m.getEmail());

            memberResponseDtos.add(memberResponseDto);

        }
        return memberResponseDtos;
    }

    @Transactional
//    Transactional를 적용하면 한 메소드 단위로 Transactional 지정된다.
    public void memberCreate(MemberRequestDto memberRequestDto) throws IllegalArgumentException{
//transactional 없으면 에러가 터져도 데이터가 들어간다.
//        try {
//            Member member = new Member(
//                    memberRequestDto.getName(),
//                    memberRequestDto.getEmail(),
//                    memberRequestDto.getPassword()
//            );
//            memberRepository.save(member);
//            throw new Exception();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        transaction 테스트
//        💡에러가 터지면 controller로 에러를 던져준다. 에러는 controller가 잡는다.
//        이미 실패했기때문에 controller에서 잡아도 실패했기 때문에 롤백은 된다.
        Member member = new Member(
                memberRequestDto.getName(),

                memberRequestDto.getEmail(),
                memberRequestDto.getPassword()
        );
        memberRepository.save(member);
//        if (member.getName().equals("kim")) {
//            throw new IllegalArgumentException();
//        }
    }

//    public Optional<Member> findbyId(int id) {
//        return memberRepository.findById(id);
//    }

    //        teacher
    public MemberResponseDto findById(int id) throws EntityNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("검색하신 Id Member 가 없습니다."));
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setPassword(member.getPassword());
        memberResponseDto.setCreate_time(member.getCreate_time());
        return memberResponseDto;
    }

    public void delete(int id){
        memberRepository.delete(memberRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    public void update(MemberRequestDto memberRequestDto) throws IllegalArgumentException{
        Member member = memberRepository.findById(memberRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        member.updateMember(memberRequestDto.getName(),memberRequestDto.getPassword());

        memberRepository.save(member);
    }
}
