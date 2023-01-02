package com.example.jpabook.jpashop.mainJpa;

import com.example.jpabook.JpaTest;
import com.example.jpabook.jpashop.Address;
import com.example.jpabook.jpashop.AddressEntity;
import com.example.jpabook.jpashop.Member;
import com.example.jpabook.jpashop.Period;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.annotations.Parent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Slf4j
@Transactional
public class Casccade extends JpaTest {


    public Casccade(EntityManager em) {
        super(em);
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("============================================");
        log.info("JPA 코드 start");

        List<Member> result = em.createQuery("select m from Member as m where m.userName like '%kim%'"
                , Member.class
        ).getResultList();

        for (Member member : result) {
            System.out.println("member = " + member);
        }

        em.createNativeQuery("select * from Member").getResultList();


        log.info("JPA 코드 end");
        log.info("============================================");
    }
}
