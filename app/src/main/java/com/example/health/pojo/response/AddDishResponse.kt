package com.example.health.pojo.response

import com.example.health.pojo.dto.MyDish

class AddDishResponse (
    val code: Int,
    val msg: String?,
    val data: MyDish
)