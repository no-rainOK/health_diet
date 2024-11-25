package com.example.health.pojo

data class MyDishResponse(
    val code: Int,
    val msg: String?,
    val data: List<MyDish> // 菜品列表
)
