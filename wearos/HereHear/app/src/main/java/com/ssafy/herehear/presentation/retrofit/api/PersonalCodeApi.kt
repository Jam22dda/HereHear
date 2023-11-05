package com.ssafy.herehear.presentation.retrofit.api

import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.ssafy.herehear.R
import com.ssafy.herehear.presentation.retrofit.RetrofitConnection
import com.ssafy.herehear.presentation.retrofit.data.response.AuthResponse
import com.ssafy.herehear.presentation.retrofit.service.PersonalCodeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun personalCodeApi(
    personalCode: String,
    setInputCode: (String) -> Unit,
    navController: NavHostController
) {
    val retrofitAPI = RetrofitConnection.getInstance().create(PersonalCodeService::class.java)

    retrofitAPI.authPersonalCode(personalCode)
        .enqueue(object : Callback<AuthResponse> {
            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>
            ) {
                val result: AuthResponse = response.body() ?: AuthResponse(false, "", -1)

                if (result.accessResult) {
                    Log.d("response personalCode", result.personalCode)
                    Log.d("response userId", result.userId.toString())

                    val navOptions = NavOptions.Builder()
                        .setPopUpTo("landing", true)
                        .build()
                    navController.navigate("map", navOptions)
                } else {
                    setInputCode("")
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("Retrofit", "personal code api 호출 실패")
            }
        })
}