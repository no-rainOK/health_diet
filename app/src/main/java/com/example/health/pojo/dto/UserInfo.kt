package com.example.health.pojo.dto

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("name") val name: String,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("sex") val sex: String?,
    @SerializedName("introduction") val introduction: String?
)