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

        log.info("JPA 끝 ============================================");
    }
}
