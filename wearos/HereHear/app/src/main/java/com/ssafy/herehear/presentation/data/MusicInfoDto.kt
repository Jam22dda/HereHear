package com.ssafy.herehear.presentation.data

data class MusicInfoDto(
    val registeredMusicId: Int = 0,
    val lng: Double = 0.0,
    val lat: Double = 0.0,
    val subject: String = "",
    val singer: String = "",
    val albumImg: String = "",
)
