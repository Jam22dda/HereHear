package com.ssafy.herehear.presentation.retrofit.service

import com.ssafy.herehear.presentation.retrofit.data.response.ApiResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface PersonalCodeService {

    @POST("/wearos/auth/{personalCode}")
    fun authPersonalCode(@Path("personalCode") personalCode: String): Call<ApiResponse>

}