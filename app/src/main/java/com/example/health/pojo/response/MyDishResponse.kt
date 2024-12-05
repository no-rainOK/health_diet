package com.example.health.pojo.response

import com.example.health.pojo.dto.MyDish

data class MyDishResponse(
    val code: Int,
    val msg: String?,
    val data: List<MyDish> // 菜品列表
)
