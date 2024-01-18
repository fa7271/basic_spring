package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
@Repository
public class JpaMemberRepository implements MemberRepository {

//    EntityManager 는 JPA의 핵심 클래스(객체)
//    Entity의 생명주기를 관리. 데이터베이스와의 모든 상호작용을 책임
//    엔티티를 대상으로 CRUD 하는 기능을 제공
    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAll() {
//        jpql : jpa의 쿼리 문법 , 객체 베이
//        장점: DB에 따라 문법이 달라지지 않는 객체 지향 언어, 컴파일 타임에서 check(SpringDataJpa의 기능).
//        단점: DB고유의 기능과 성능을 극대화하기는 어려움
        List<Member> members = entityManager.createQuery("select m from Member m", Member.class).getResultList();
//        테이블 대소문자 맞춰야함
        return members;
    }

    @Override
    public Member save(Member member) {
//        persist : 전달된 엔티티(member) 가 EntityManager의 관리상태가 디도록 만들어주고..
//        트랜잭션이 커밋될 때 데이터베이스에 저장
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
//        find 메서드는 pk를 매개변수로 준다.
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public void delete(Member member) {
//        remove 메서드 사용
//        update 경우 merge메서드 사용

    }
/* 그 외 컬럼으로 조회할때. -> 순수 jpa
    public List<Member> findByName(String name) {
        List<Member> members = entityManager
                .createQuery("select m from Member m where m.name = : name", Member.class)
                .setParameter("name",name).getResultList();
        return members;
    }*/
}
