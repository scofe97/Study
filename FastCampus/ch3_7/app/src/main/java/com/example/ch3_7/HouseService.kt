package com.example.ch3_7

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("https://run.mocky.io/v3/8203e2c0-e4db-470d-82dc-98784ad1670e")
    fun getHouseList() : Call<HouseDto>

}