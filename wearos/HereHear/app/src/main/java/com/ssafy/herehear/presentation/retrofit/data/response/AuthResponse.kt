package com.ssafy.herehear.presentation.retrofit.data.response

data class AuthResponse(
    val accessResult: Boolean,
    val personalCode: String,
    val userId: Int,
)
