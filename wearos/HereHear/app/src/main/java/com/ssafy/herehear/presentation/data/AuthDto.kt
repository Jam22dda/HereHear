package com.ssafy.herehear.presentation.data

data class AuthDto(
    val accessResult: Boolean = false,
    val personalCode: String = "",
    val userId: Int = 0,
)
