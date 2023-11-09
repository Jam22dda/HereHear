package com.ssafy.herehear.presentation.retrofit.api

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ssafy.herehear.presentation.data.AroundMusicDto
import com.ssafy.herehear.presentation.data.AroundMusicListResponse
import com.ssafy.herehear.presentation.retrofit.RetrofitConnection
import com.ssafy.herehear.presentation.data.MusicInfoDto
import com.ssafy.herehear.presentation.data.MusicDetailDto
import com.ssafy.herehear.presentation.data.MusicDetailResponse
import com.ssafy.herehear.presentation.data.MusicListResponse
import com.ssafy.herehear.presentation.retrofit.service.MusicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun musicListRequest(
    personalCode: String,
    musicList: SnapshotStateList<MusicInfoDto>,
) {
    val retrofit = RetrofitConnection.getInstance().create(MusicService::class.java)

    retrofit.getMusicList(personalCode)
        .enqueue(object : Callback<MusicListResponse> {
            override fun onResponse(
                call: Call<MusicListResponse>,
                response: Response<MusicListResponse>
            ) {
                Log.d("[musicListRequest]", response.toString())
                musicList.clear()
                musicList.addAll(response.body()?.data ?: listOf())
                Log.d("[musicListRequest]", musicList.toString())
            }

            override fun onFailure(call: Call<MusicListResponse>, t: Throwable) {
                Log.d("MusicRequest", "music request fail")
            }
        })
}

fun musicDetailRequest(
    personalCode: String,
    musicId: Int,
    musicDetail: SnapshotStateList<MusicDetailDto>,
) {
    val retrofit = RetrofitConnection.getInstance().create(MusicService::class.java)

    retrofit.getMusicDetail(personalCode, musicId)
        .enqueue(object : Callback<MusicDetailResponse> {
            override fun onResponse(
                call: Call<MusicDetailResponse>,
                response: Response<MusicDetailResponse>
            ) {
                musicDetail.add(response.body()?.data ?: MusicDetailDto())
                Log.d("[musicDetailRequest]", musicDetail.toString())
            }

            override fun onFailure(call: Call<MusicDetailResponse>, t: Throwable) {
                Log.d("[musicDetailRequest]", "music detail request fail")
            }
        })
}

fun aroundMusicRequest(
    personalCode: String,
    lat: Double,
    lng: Double,
    aroundMusicList: SnapshotStateList<AroundMusicDto>,
) {
    val retrofit = RetrofitConnection.getInstance().create(MusicService::class.java)

    retrofit.getAroundMusicList(personalCode, lat, lng)
        .enqueue(object : Callback<AroundMusicListResponse> {
            override fun onResponse(
                call: Call<AroundMusicListResponse>,
                response: Response<AroundMusicListResponse>
            ) {
                Log.d("[aroundMusicRequest]", response.toString())
                aroundMusicList.clear()
                aroundMusicList.addAll(response.body()?.data ?: listOf())
                Log.d("[aroundMusicRequest]", aroundMusicList.toString())
            }

            override fun onFailure(call: Call<AroundMusicListResponse>, t: Throwable) {
                Log.d("[aroundMusicRequest]", "around music request fail")
            }
        })
}