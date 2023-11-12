package com.ssafy.herehear.presentation.data

data class LikeMusicDto(
    val registeredMusicId: Int = 0,
    val subject: String = "",
    val singer: String = "",
    val albumImg: String = "",
    val like: Boolean = false,
)
