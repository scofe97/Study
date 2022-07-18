package com.example.ch4_2.service

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {

    @GET("/v3/f78d2012-634e-4665-a686-351ab95c90fa")
    fun listMusics() : Call<MusicDto>
}