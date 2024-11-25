package com.example.health.network

import com.example.health.pojo.MyDishResponse
import com.example.health.pojo.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DishApi {
    @GET("/user/my_dish/{userId}")
    fun getDishesByUserId(@Path("userId") userId: Int): Call<MyDishResponse>
}


