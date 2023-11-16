package com.ssafy.herehear.presentation.retrofit.service

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import com.ssafy.herehear.presentation.data.AroundMusicListResponse
import com.ssafy.herehear.presentation.data.MusicDetailResponse
import com.ssafy.herehear.presentation.data.MusicListResponse
import com.ssafy.herehear.presentation.data.request.PostLikeRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicService {

    @GET("music/list")
    fun getMusicList(
        @Header("personalCode") personalCode: String,
    ): Call<MusicListResponse>

    @GET("music/{musicId}")
    fun getMusicDetail(
        @Header("personalCode") personalCode: String,
        @Path("musicId") musicId: Int,
    ): Call<MusicDetailResponse>

    @GET("music/around/list")
    fun getAroundMusicList(
        @Header("personalCode") personalCode: String,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
    ): Call<AroundMusicListResponse>

    @POST("like")
    fun postLike(
        @Header("personalCode") personalCode: String,
        @Body postLikeRequest: PostLikeRequest
    ): Call<Unit>
}