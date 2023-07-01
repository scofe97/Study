package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.domain.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    void testChangeName() {
        // ObjectMother 패턴 (검색해볼 것)
        /*
        LongStream.range(0, 10)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println(member.getNickname());
                });
        */

        var member = MemberFixtureFactory.crate();
        var expected = "pnu";
        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @Test
    @DisplayName("회원의 닉네님은 10글자를 초과할 수 없다.")
    void testNicknameMaxLength() {
        // ObjectMother 패턴 (검색해볼 것)
        /*
        LongStream.range(0, 10)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println(member.getNickname());
                });
        */

        var member = MemberFixtureFactory.crate();
        var overMaxLengthName = "pnpupupupupupu";

        Assertions.assertThrows(IllegalArgumentException.class, () -> member.changeNickname(overMaxLengthName));
    }
}