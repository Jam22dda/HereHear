package com.ssafy.herehear.presentation.retrofit.data.response

import com.ssafy.herehear.presentation.data.AuthDto

data class ApiResponse(
    val code: String,
    val message: String,
    val data: AuthDto
)
