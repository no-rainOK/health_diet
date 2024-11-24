package com.example.health.pojo

class MyDishResponse {
    data class DishResponse(
        val code: Int,
        val msg: String?,
        val data: List<MyDish> // 菜品列表
    )
}