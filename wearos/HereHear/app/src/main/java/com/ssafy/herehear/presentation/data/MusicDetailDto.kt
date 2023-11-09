package com.ssafy.herehear.presentation.data

data class MusicDetailDto(
    val registeredMusicId: Int = 0,
    val lng: Double = 0.0,
    val lat: Double = 0.0,
    val comment: String = "",
    val subject: String = "",
    val singer: String = "",
    val albumImg: String = "",
    val releaseTime: String = "",
    val createTime: String = "",
    val memberId: String = "",
    val nickname: String = "",
    val characterImage: String = "",
    val like: Boolean = false,
    val occasionName: List<String> = listOf(),
)

