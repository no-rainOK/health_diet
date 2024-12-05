package com.example.health.pojo.response

import com.example.health.pojo.dto.UserData

data class UserResponse(
    val code: Int,     // 用于判断请求是否成功
    val msg: String?,  // 返回的消息，可以为空
    val data: UserData?  // 成功时返回的详细信息，如 "登录成功"
)
