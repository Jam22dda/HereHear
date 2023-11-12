package com.ssafy.herehear.presentation.retrofit.api

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ssafy.herehear.presentation.data.AchievementDto
import com.ssafy.herehear.presentation.data.AchievementResponse
import com.ssafy.herehear.presentation.data.FollowerDto
import com.ssafy.herehear.presentation.data.FollowerResponse
import com.ssafy.herehear.presentation.data.FollowingDto
import com.ssafy.herehear.presentation.data.FollowingResponse
import com.ssafy.herehear.presentation.data.LikeMusicDto
import com.ssafy.herehear.presentation.data.LikeMusicResponse
import com.ssafy.herehear.presentation.data.MemberInfoDto
import com.ssafy.herehear.presentation.data.MemberInfoResponse
import com.ssafy.herehear.presentation.data.RecentMusicDto
import com.ssafy.herehear.presentation.data.RecentMusicResponse
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

fun getFollowingList(
    personalCode: String,
    followingList: SnapshotStateList<FollowingDto>,
) {
    Log.d("[getFollowingList]", "getFollowingList API 호출")
    val retrofit = RetrofitConnection.getInstance().create(MyPageService::class.java)

    retrofit.getFollowingList(personalCode)
        .enqueue(object : Callback<FollowingResponse> {
            override fun onResponse(
                call: Call<FollowingResponse>,
                response: Response<FollowingResponse>
            ) {
                followingList.clear()
                followingList.addAll(response.body()?.data ?: listOf())
            }

            override fun onFailure(call: Call<FollowingResponse>, t: Throwable) {
            }
        })
}

fun getFollowerList(
    personalCode: String,
    followerList: SnapshotStateList<FollowerDto>,
) {
    Log.d("[getFollowingList]", "getFollowingList API 호출")
    val retrofit = RetrofitConnection.getInstance().create(MyPageService::class.java)

    retrofit.getFollowerList(personalCode)
        .enqueue(object : Callback<FollowerResponse> {
            override fun onResponse(
                call: Call<FollowerResponse>,
                response: Response<FollowerResponse>
            ) {
                followerList.clear()
                followerList.addAll(response.body()?.data ?: listOf())
            }

            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
            }
        })
}

fun getRecentMusicList(
    personalCode: String,
    recentMusicList: SnapshotStateList<RecentMusicDto>
) {
    Log.d("[getRecentMusicList]", "getRecentMusicList API 호출")
    val retrofit = RetrofitConnection.getInstance().create(MyPageService::class.java)

    retrofit.getRecentMusicList(personalCode)
        .enqueue(object : Callback<RecentMusicResponse> {
            override fun onResponse(
                call: Call<RecentMusicResponse>,
                response: Response<RecentMusicResponse>
            ) {
                recentMusicList.clear()
                recentMusicList.addAll(response.body()?.data ?: listOf())
            }

            override fun onFailure(call: Call<RecentMusicResponse>, t: Throwable) {
            }
        })
}

fun getLikeMusicList(
    personalCode: String,
    likeMusicList: SnapshotStateList<LikeMusicDto>,
    isFavoriteList: SnapshotStateList<Boolean>,
) {
    Log.d("[getLikeMusicList]", "getLikeMusicList API 호출")
    val retrofit = RetrofitConnection.getInstance().create(MyPageService::class.java)

    retrofit.getLikeMusicList(personalCode)
        .enqueue(object : Callback<LikeMusicResponse> {
            override fun onResponse(
                call: Call<LikeMusicResponse>,
                response: Response<LikeMusicResponse>
            ) {
                likeMusicList.clear()
                likeMusicList.addAll(response.body()?.data ?: listOf())
                isFavoriteList.clear()

                for (item in likeMusicList) {
                    isFavoriteList.add(item.like)
                }
            }

            override fun onFailure(call: Call<LikeMusicResponse>, t: Throwable) {
            }
        })
}