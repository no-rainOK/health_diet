package com.example.health.pojo.response

import com.example.health.pojo.dto.UserInfo
import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("msg") val msg: String,
    @SerializedName("data") val data: UserInfo
)
