package com.ssafy.herehear.presentation.retrofit.service

import com.ssafy.herehear.presentation.data.AchievementResponse
import com.ssafy.herehear.presentation.data.MemberInfoDto
import com.ssafy.herehear.presentation.data.MemberInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MyPageService {

    @GET("member/info")
    fun getMemberInfo(
        @Header("personalCode") personalCode: String
    ): Call<MemberInfoResponse>

    @GET("achievement/{achievementId}")
    fun getAchievementInfo(
        @Header("personalCode") personalCode: String,
        @Path("achievementId") achievementId: Int
    ): Call<AchievementResponse>

}