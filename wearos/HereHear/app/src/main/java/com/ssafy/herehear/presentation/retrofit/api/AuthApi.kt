package com.ssafy.herehear.presentation.retrofit.api

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.ssafy.herehear.presentation.data.AuthDto
import com.ssafy.herehear.presentation.data.AuthResponse
import com.ssafy.herehear.presentation.retrofit.RetrofitConnection
import com.ssafy.herehear.presentation.retrofit.service.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun authRequest(
    personalCode: String,
    isLoginSuccess: MutableState<String>,
    navController: NavHostController,
    route: String,
) {
    Log.d("[authRequest]", "authRequest API 호출, 인증성공시 map 화면으로 이동")
    val retrofit = RetrofitConnection.getInstance().create(AuthService::class.java)

    retrofit.authPersonalCode(personalCode)
        .enqueue(object : Callback<AuthResponse> {
            override fun onResponse(
                call: Call<AuthResponse>,
                authResponse: Response<AuthResponse>
            ) {
                val apiResult: AuthDto = authResponse.body()?.data ?: AuthDto()

                Log.d("[authRequest]", "authRequest API 호출 성공, result = ${apiResult.accessResult}")
                if (apiResult.accessResult) {
                    isLoginSuccess.value = "success"

                    // 여기에 navigation map 으로 이동하는 로직
                    navController.navigate(route) {
                        popUpTo(0) { inclusive = true }
                    }
                } else {
                    isLoginSuccess.value = "fail"
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("[authRequest]", "authRequest API 호출 실패")
                isLoginSuccess.value = "fail"
            }
        })
}