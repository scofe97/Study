package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

// 과거 정보를 기록하는 History의 경우 정규화 대상이아니다!
@Getter
public class MemberNicknameHistory {

    final private Long id;
    final private Long memberId;
    final private String nickname;
    final private LocalDateTime createdAt;

    @Builder
    public MemberNicknameHistory(Long id, Long memberId, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.nickname = nickname;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;;
    }
}
