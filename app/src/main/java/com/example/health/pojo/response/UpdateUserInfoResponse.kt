package com.example.health.pojo.response

import com.example.health.pojo.dto.UpdateUserInfo

class UpdateUserInfoResponse (
    val code: Int,
    val msg: String?,
    val data: UpdateUserInfo?
)