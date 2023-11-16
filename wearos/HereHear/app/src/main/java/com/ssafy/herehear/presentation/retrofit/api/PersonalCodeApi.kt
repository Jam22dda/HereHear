package com.ssafy.herehear.presentation.retrofit.api

import android.content.Context
import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.ssafy.herehear.presentation.retrofit.RetrofitConnection
import com.ssafy.herehear.presentation.retrofit.service.AuthService
import com.ssafy.herehear.presentation.util.deletePersonalCodeFile
import com.ssafy.herehear.presentation.util.writePersonalCodeFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun personalCodeApi(
    personalCode: String,
    setInputCode: (String) -> Unit,
    navController: NavHostController,
    baseContext: Context
) {
    val retrofitAPI = RetrofitConnection.getInstance().create(AuthService::class.java)

    retrofitAPI.authPersonalCode(personalCode)
        .enqueue(object : Callback<com.ssafy.herehear.presentation.data.AuthResponse> {
            override fun onResponse(
                call: Call<com.ssafy.herehear.presentation.data.AuthResponse>,
                authResponse: Response<com.ssafy.herehear.presentation.data.AuthResponse>
            ) {
                Log.d("Retrofit", "personal code api 호출 성공")
                Log.d("Retrofit", authResponse.body().toString())
                val result: com.ssafy.herehear.presentation.data.AuthResponse? = authResponse.body()

                if (result?.data?.accessResult == true) {
                    baseContext.deletePersonalCodeFile("personalCode.txt")
                    baseContext.writePersonalCodeFile("personalCode.txt", result.data.personalCode)

                    val navOptions = NavOptions.Builder()
                        .setPopUpTo("landing", true)
                        .build()
                    navController.navigate("map", navOptions)
                } else {
                    setInputCode("")
                }
            }

            override fun onFailure(call: Call<com.ssafy.herehear.presentation.data.AuthResponse>, t: Throwable) {
                Log.d("Retrofit", "personal code api 호출 실패")
            }
        })
}