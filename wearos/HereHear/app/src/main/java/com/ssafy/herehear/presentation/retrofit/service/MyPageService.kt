package com.ssafy.herehear.presentation.retrofit.service

import com.ssafy.herehear.presentation.data.AchievementResponse
import com.ssafy.herehear.presentation.data.FollowerResponse
import com.ssafy.herehear.presentation.data.FollowingResponse
import com.ssafy.herehear.presentation.data.LikeMusicResponse
import com.ssafy.herehear.presentation.data.MemberInfoResponse
import com.ssafy.herehear.presentation.data.RecentMusicResponse
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

    @GET("member/following")
    fun getFollowingList(
        @Header("personalCode") personalCode: String
    ): Call<FollowingResponse>

    @GET("member/follower")
    fun getFollowerList(
        @Header("personalCode") personalCode: String
    ): Call<FollowerResponse>

    @GET("history/list")
    fun getRecentMusicList(
        @Header("personalCode") personalCode: String
    ): Call<RecentMusicResponse>

    @GET("like/list")
    fun getLikeMusicList(
        @Header("personalCode") personalCode: String
    ): Call<LikeMusicResponse>
}