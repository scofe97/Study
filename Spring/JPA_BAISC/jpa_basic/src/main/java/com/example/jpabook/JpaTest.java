package com.example.jpabook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Slf4j
@RequiredArgsConstructor
@Transactional
public class JpaTest {

    protected final EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {}
}
