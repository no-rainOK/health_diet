package com.example.health.pojo.response

import com.example.health.pojo.dto.MyDish

class UpdateDishResponse (
    val code: Int,
    val msg: String?,
    val data: MyDish
)