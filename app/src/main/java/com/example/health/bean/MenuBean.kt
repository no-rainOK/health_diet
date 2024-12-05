package com.example.health.bean

import java.io.Serializable

data class MenuBean(
    val name: String?,
    val step: String?,
    val image: String?,
    val intro: String?
) : Serializable

