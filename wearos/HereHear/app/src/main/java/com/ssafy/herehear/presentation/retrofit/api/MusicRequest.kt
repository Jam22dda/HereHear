package com.ssafy.herehear.presentation.retrofit.api

import android.util.Log
import androidx.compose.runtime.MutableState
import com.ssafy.herehear.presentation.retrofit.RetrofitConnection
import com.ssafy.herehear.presentation.retrofit.data.response.MusicAroundData
import com.ssafy.herehear.presentation.retrofit.data.response.MusicDetailData
import com.ssafy.herehear.presentation.retrofit.data.response.MusicDetailResponse
import com.ssafy.herehear.presentation.retrofit.data.response.MusicResponse
import com.ssafy.herehear.presentation.retrofit.service.MusicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun musicRequest(
    personalCode: String,
    musicList: MutableList<MusicAroundData>,
) {
    val retrofit = RetrofitConnection.getInstance().create(MusicService::class.java)

    retrofit.getMusicList(personalCode)
        .enqueue(object : Callback<MusicResponse> {
            override fun onResponse(call: Call<MusicResponse>, response: Response<MusicResponse>) {
                Log.d("MusicRequest", response.toString())
                musicList.clear()
                musicList.addAll(response.body()?.data ?: listOf())
                Log.d("MusicRequest", musicList.toString())
            }

            override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                Log.d("MusicRequest", "music request fail")
            }
        })
}

fun musicDetailRequest(
    personalCode: String,
    musicId: Int,
    musicDetail: MutableState<MusicDetailData>,
) {
    val retrofit = RetrofitConnection.getInstance().create(MusicService::class.java)

    retrofit.getMusicDetail(personalCode, musicId)
        .enqueue(object : Callback<MusicDetailResponse> {
            override fun onResponse(
                call: Call<MusicDetailResponse>,
                response: Response<MusicDetailResponse>
            ) {
                musicDetail.value = response.body()?.data ?: MusicDetailData()
                Log.d("[musicDetailRequest]", musicDetail.toString())
            }

            override fun onFailure(call: Call<MusicDetailResponse>, t: Throwable) {
                Log.d("[musicDetailRequest]", "music detail request fail")
            }
        })
}