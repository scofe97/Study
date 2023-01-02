package com.example.jpql.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        //given
        //Member item = new Member(1L, "이름1"); // 비영속 상태

        //when
        //Member savedItem = memberRepository.save(item); // 영속 상태

        //then
        //Member findItem = memberRepository.findById(item.getId()).get();
        //.assertThat(findItem).isEqualTo(savedItem);
    }
}
