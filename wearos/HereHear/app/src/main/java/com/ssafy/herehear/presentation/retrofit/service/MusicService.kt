package com.ssafy.herehear.presentation.retrofit.service

import com.ssafy.herehear.presentation.retrofit.data.response.MusicDetailResponse
import com.ssafy.herehear.presentation.retrofit.data.response.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MusicService {

    @GET("music/list")
    fun getMusicList(
        @Header("personalCode") personalCode: String,
    ): Call<MusicResponse>

    @GET("music/{musicId}")
    fun getMusicDetail(
        @Header("personalCode") personalCode: String,
        @Path("musicId") musicId: Int,
    ): Call<MusicDetailResponse>

}