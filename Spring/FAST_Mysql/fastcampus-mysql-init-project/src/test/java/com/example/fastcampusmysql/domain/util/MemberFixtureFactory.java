package com.example.fastcampusmysql.domain.util;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {

    static public Member crate(){
        var parameter = new EasyRandomParameters();
        return new EasyRandom(parameter).nextObject(Member.class);
    }

    // easy-random 사용해서 랜덤값으로 생성해줌
    static public Member create(Long seed){
        var parameter = new EasyRandomParameters().seed(seed);
        return new EasyRandom(parameter).nextObject(Member.class);
    }
}
