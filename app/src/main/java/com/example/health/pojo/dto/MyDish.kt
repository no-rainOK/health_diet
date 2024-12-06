package com.example.health.pojo.dto

data class MyDish(
    val id: Int, //菜品id
    val image: String?,//菜品图片
    val name: String, // 菜品名称
    val step: String  // 制作步骤
)
