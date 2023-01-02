package com.example.jpql;

import com.example.jpql.entity.Member;
import com.example.jpql.entity.MemberDto;
import com.example.jpql.entity.MemberType;
import com.example.jpql.entity.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Transactional
public class JpaTest {

    protected final EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("JPA 시작 =============================================");

        Team team = new Team();
        team.setName("teamA");
        em.persist(team);

        Member member = new Member();
        member.setUserName("member");
        member.setAge(10);
        member.setTeam(team);
        member.setType(MemberType.ADMIN);
        em.persist(member);

        em.flush();
        em.clear();

        String query2 = "SELECT m FROM Member m LEFT OUTER JOIN m.team te";

        String query = "select " +
                "case when m.age <= 10 then '학생요금' " +
                "   when m.age >= 60 then '경로요금' " +
                "   else '일반요금' end " +
                "from Member m ";

        List<String> resultList = em.createQuery(query2, String.class).getResultList();

        log.info("JPA 끝 ============================================");
    }
}
