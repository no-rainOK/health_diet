package com.example.health.bean

import java.io.Serializable

data class FoodBean(
    val name: String?,
    val taboo: String?,
    val image: String?, // 图片路径
    val intro: String?
) : Serializable
