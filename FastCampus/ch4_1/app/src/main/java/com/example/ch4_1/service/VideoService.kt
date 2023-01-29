package com.example.ch4_1.service

import com.example.ch4_1.model.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {

    @GET("v3/4c2bf820-fcc2-4bfc-bfc3-cfa656d9022a")
    fun listVideos() : Call<VideoDto>

}