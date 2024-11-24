package com.example.health.network

import com.example.health.pojo.UserResponse
import com.example.health.pojo.UserLogin
import com.example.health.pojo.UserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("user/register")
    fun register(@Body userRegister: UserRegister): Call<UserResponse>

    @POST("user/login")
    fun login(@Body userLogin: UserLogin): Call<UserResponse>



    companion object {
        // 通过 RetrofitClient 获取 Retrofit 实例，并创建 ApiService
        fun create(): UserApi {
            return RetrofitClient.instance.create(UserApi::class.java)
        }
    }

}
