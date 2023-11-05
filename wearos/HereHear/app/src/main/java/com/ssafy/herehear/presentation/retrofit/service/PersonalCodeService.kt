package com.ssafy.herehear.presentation.retrofit.service

import com.ssafy.herehear.presentation.retrofit.data.response.AuthResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonalCodeService {

    @GET("/api/wearos/auth/{personalCode}")
    fun authPersonalCode(@Path("personalCode") personalCode: String): Call<AuthResponse>

}