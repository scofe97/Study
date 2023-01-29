package com.example.jpabook.jpashop.mainJpa;

import com.example.jpabook.JpaTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Slf4j
@Transactional
public class Basic extends JpaTest {


    public Basic(EntityManager em) {
        super(em);
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("============================================");
        log.info("JPA 코드 start");


//        Team team = new Team();
//        team.setName("teamA");
//        em.persist(team);
//
//        Member newMember = new Member();
//        newMember.setUserName("hello");
//        newMember.setTeam(team);
//        em.persist(newMember);
//
//        em.flush();
//        em.clear();
//
//         em.find(Member.class, newMember.getId());
//
//        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
//        Member member = em.find(Member.class, newMember.getId());
//
//         Member member = em.find(Member.class, newMember.getId());
//        Member reference = em.getReference(Member.class, newMember.getId());
//
//        System.out.println("before member = " + reference.getClass()); // 동일
//        System.out.println("after member = " + reference.getClass()); // 동일
//
//        System.out.println("member = " + reference.getClass()); // 동일
//        System.out.println("member = " + member.getClass()); // 동일
//        // 만약 getReference를 먼저하면 프록시로 같아짐
//        // JPA는 항상 == true를 보장해야함
//
//        getPrintMember(reference); // member 단독 사용
//        getPrintMemberAndTeam(reference); // member, team 둘다 사용
//
//         영속성이 꺼지거나, 준영속성이면 문제발생
//        em.close();
//        em.detach(reference);
//        reference.getId();
//
//        System.out.println("refMember = " + reference.getUserName());
//
//        log.info("JPA 코드 end");
//        log.info("============================================");
    }

//    private void getPrintMember(Member member) {
//        String userName = member.getUserName();
//        System.out.println("userName = " + userName);
//    }
//
//    private void getPrintMemberAndTeam(Member member) {
//        String userName = member.getUserName();
//        System.out.println("userName = " + userName);
//
//        Team team = member.getTeam();
//        System.out.println("team = " + team.getName());
//    }
}
