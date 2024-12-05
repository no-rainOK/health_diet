package com.example.health.network

import com.example.health.pojo.response.AddDishResponse
import com.example.health.pojo.dto.AddMyDish
import com.example.health.pojo.response.DeleteDishResponse
import com.example.health.pojo.response.MyDishResponse
import com.example.health.pojo.response.UpdateDishResponse
import com.example.health.pojo.dto.UpdateMyDish
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DishApi {
    @GET("/user/my_dish/{userId}")
    fun getDishesByUserId(@Path("userId") userId: Int): Call<MyDishResponse>

    @POST("/user/my_dish")
    fun addDish(@Body addMyDish: AddMyDish):Call<AddDishResponse>

    @DELETE("user/my_dish/{id}")
    fun deleteDish(@Path("id") id: Int): Call<DeleteDishResponse>

    @PUT("user/my_dish")
    fun updateDish(@Body updateMyDish: UpdateMyDish): Call<UpdateDishResponse>
}


