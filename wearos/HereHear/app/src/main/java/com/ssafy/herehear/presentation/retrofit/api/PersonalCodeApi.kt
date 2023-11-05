package com.ssafy.herehear.presentation.retrofit.api

import android.content.Context
import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.ssafy.herehear.presentation.retrofit.RetrofitConnection
import com.ssafy.herehear.presentation.retrofit.data.response.ApiResponse
import com.ssafy.herehear.presentation.retrofit.data.response.AuthResponse
import com.ssafy.herehear.presentation.retrofit.service.PersonalCodeService
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
    val retrofitAPI = RetrofitConnection.getInstance().create(PersonalCodeService::class.java)

    retrofitAPI.authPersonalCode(personalCode)
        .enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                Log.d("Retrofit", "personal code api 호출 성공")
                Log.d("Retrofit", response.body().toString())
                val result: ApiResponse? = response.body()

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

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("Retrofit", "personal code api 호출 실패")
            }
        })
}