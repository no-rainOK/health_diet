package com.example.health.network

import com.example.health.pojo.response.AddDishResponse
import com.example.health.pojo.dto.AddMyDish
import com.example.health.pojo.response.DeleteDishResponse
import com.example.health.pojo.response.MyDishResponse
import com.example.health.pojo.response.UpdateDishResponse
import com.example.health.pojo.dto.UpdateMyDish
import com.example.health.pojo.response.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface DishApi {
    @GET("dish/{userId}")
    fun getDishesByUserId(@Path("userId") userId: Int): Call<MyDishResponse>

    @POST("dish")
    fun addDish(@Body addMyDish: AddMyDish):Call<AddDishResponse>

    @DELETE("dish/{id}")
    fun deleteDish(@Path("id") id: Int): Call<DeleteDishResponse>

    @PUT("dish")
    fun updateDish(@Body updateMyDish: UpdateMyDish): Call<UpdateDishResponse>

    @Multipart
    @POST("dish/upload/{id}")
    fun uploadImage(@Path("id") id: Int,@Part file: MultipartBody.Part): Call<UploadResponse>

    companion object {
        // 通过 RetrofitClient 获取 Retrofit 实例，并创建 ApiService
        fun create(): DishApi {
            return RetrofitClient.instance.create(DishApi::class.java)
        }
    }
}


