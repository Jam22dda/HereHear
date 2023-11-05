package com.ssafy.herehear.presentation.retrofit.api

import android.util.Log
import com.ssafy.herehear.presentation.retrofit.RetrofitConnection
import com.ssafy.herehear.presentation.retrofit.data.response.ApiResponse
import com.ssafy.herehear.presentation.retrofit.data.response.AuthResponse
import com.ssafy.herehear.presentation.retrofit.service.PersonalCodeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun authRequest(personalCode: String): AuthResponse? {
    val retrofit = RetrofitConnection.getInstance().create(PersonalCodeService::class.java)

    var result: AuthResponse? = null
    retrofit.authPersonalCode(personalCode).enqueue(object : Callback<ApiResponse> {
        override fun onResponse(
            call: Call<ApiResponse>,
            response: Response<ApiResponse>
        ) {
            val apiResult: AuthResponse = response.body()?.data ?: AuthResponse(false, "", -1)
            result = apiResult
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            Log.d("Retrofit", "personal code api 호출 실패")
        }
    })

    return result
}