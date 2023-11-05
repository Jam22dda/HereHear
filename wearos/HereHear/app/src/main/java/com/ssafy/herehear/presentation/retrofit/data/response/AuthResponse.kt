package com.ssafy.herehear.presentation.retrofit.data.response

data class ApiResponse(
    val code: String,
    val message: String,
    val data: AuthResponse
)

data class AuthResponse(
    val accessResult: Boolean,
    val personalCode: String,
    val userId: Int,
)
