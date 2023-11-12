package com.ssafy.herehear.presentation.retrofit.api

import android.util.Log
import androidx.compose.runtime.MutableState
import com.ssafy.herehear.presentation.data.AchievementDto
import com.ssafy.herehear.presentation.data.AchievementResponse
import com.ssafy.herehear.presentation.data.MemberInfoDto
import com.ssafy.herehear.presentation.data.MemberInfoResponse
import com.ssafy.herehear.presentation.retrofit.RetrofitConnection
import com.ssafy.herehear.presentation.retrofit.service.MyPageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getMyProfile(
    personalCode: String,
    myProfile: MutableState<MemberInfoDto>,
) {
    Log.d("[getMyProfile]", "getMyProfile API 호출")
    val retrofit = RetrofitConnection.getInstance().create(MyPageService::class.java)

    retrofit.getMemberInfo(personalCode)
        .enqueue(object : Callback<MemberInfoResponse> {
            override fun onResponse(
                call: Call<MemberInfoResponse>,
                response: Response<MemberInfoResponse>
            ) {
                myProfile.value = response.body()?.data ?: MemberInfoDto()
            }

            override fun onFailure(call: Call<MemberInfoResponse>, t: Throwable) {
            }
        })
}

fun getAchievement(
    personalCode: String,
    achievementId: Int,
    achievement: MutableState<AchievementDto>,
) {
    Log.d("[getAchievement]", "getAchievement API 호출")
    val retrofit = RetrofitConnection.getInstance().create(MyPageService::class.java)

    retrofit.getAchievementInfo(personalCode, achievementId)
        .enqueue(object : Callback<AchievementResponse> {
            override fun onResponse(
                call: Call<AchievementResponse>,
                response: Response<AchievementResponse>
            ) {
                achievement.value = response.body()?.data ?: AchievementDto()
            }

            override fun onFailure(call: Call<AchievementResponse>, t: Throwable) {
            }
        })
}