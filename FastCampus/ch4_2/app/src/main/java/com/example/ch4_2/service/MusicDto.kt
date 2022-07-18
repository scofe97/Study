package com.example.ch4_2.service

data class MusicDto(
    // 서버에서 내려오는 모델 -> entity
    // 뷰에서 사용하는 뮤직 모델 -> 모델 분리
    val musics : List<MusicEntity>
) {
}