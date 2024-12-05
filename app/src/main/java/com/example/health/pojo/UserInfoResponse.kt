package com.example.health.pojo

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("msg") val msg: String,
    @SerializedName("data") val data: UserInfo
)
