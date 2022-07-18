package com.example.ch3_4.api

import com.example.ch3_4.model.BestSellerDTO
import com.example.ch3_4.model.SearchBookDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    // 베스트셀러와 검색 API 를 가져올거임

    // GET , POST
    // GET -> 데이터를 요청할 때 서버에서 반환 URL 에 모두 넣어서
    // POST -> 바디에 넣어서

    // Query 방식 -> 실제로 함수를 호출할 때 필요한 파라미터로 값을 전달함함

   @GET("/api/search.api?output=json")
    fun getBookByName(
        @Query("key") apiKey : String,
        @Query("query") keyword : String
    ): Call<SearchBookDTO>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDTO>
}