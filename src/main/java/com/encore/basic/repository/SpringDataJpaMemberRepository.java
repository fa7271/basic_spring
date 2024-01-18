package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// spring data jpa의 기본기능을 쓰기 위해서는 JpaRepository를 상속해야한다
// 상속시에 entity명과 해당 ENTITY의 PK타입을 명시

// 실질적인 구현클래스와 스펙은 simpleJpaRepository에 있고
// 실질적인 구동상황에서 hibernate구현체에 동작을 위임.

public interface SpringDataJpaMemberRepository extends MemberRepository, JpaRepository<Member, Integer> {

}
// class 는 다중상속 불가능,
// 구현체가 springdatajpa기술을 통해 주입