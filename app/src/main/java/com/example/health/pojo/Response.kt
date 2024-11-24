package com.example.health.pojo

import java.util.Objects

data class Response(
    val code: Int,     // 用于判断请求是否成功
    val msg: String?,  // 返回的消息，可以为空
    val data: Objects?
)