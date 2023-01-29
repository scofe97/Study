package com.example.ch3_6.home

data class ArticleModel(
    val sellerId : String,
    val title : String,
    val createAt : Long,
    val price : String,
    val imageUrl : String
){
    // 리얼타임 데이터베이스의 데이터 모델로 쓰기위해선, 기본 생성자 필수
    constructor() : this("","",0,"","")

}