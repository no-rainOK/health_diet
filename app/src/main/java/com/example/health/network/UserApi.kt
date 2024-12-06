package com.example.health.network

import com.example.health.pojo.dto.UpdateUserInfo
import com.example.health.pojo.response.UpdateUserInfoResponse
import com.example.health.pojo.response.UploadResponse
import com.example.health.pojo.response.UserInfoResponse
import com.example.health.pojo.response.UserResponse
import com.example.health.pojo.dto.UserLogin
import com.example.health.pojo.dto.UserRegister
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface UserApi {

    @POST("user/register")
    fun register(@Body userRegister: UserRegister): Call<UserResponse>

    @POST("user/login")
    fun login(@Body userLogin: UserLogin): Call<UserResponse>

//    @GET("user/avatar/{id}")
//    fun getAvatar(@Path("id") userId: Int): Call<Response>

    @GET("user/info/{id}")
    fun getUserInfo(@Path("id") id: Int): Call<UserInfoResponse>

    @Multipart
    @POST("user/upload/{id}")
    fun uploadAvatar(@Path("id") id: Int, @Part file: MultipartBody.Part): Call<UploadResponse>

    @PUT("user/info")
    fun updateUserInfo(@Body updateUserInfo: UpdateUserInfo): Call<UpdateUserInfoResponse>

    companion object {
        // 通过 RetrofitClient 获取 Retrofit 实例，并创建 ApiService
        fun create(): UserApi {
            return RetrofitClient.instance.create(UserApi::class.java)
        }
    }

}
