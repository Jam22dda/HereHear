package com.ssafy.herehear.presentation.retrofit.data.response

data class MusicResponse(
    val code: String,
    val message: String,
    val data: List<MusicAroundData>
)

data class MusicAroundData(
    val registeredMusicId: Int,
    val lng: Double,
    val lat: Double,
    val subject: String,
    val singer: String,
    val albumImg: String,
)
