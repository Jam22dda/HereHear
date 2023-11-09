package com.ssafy.herehear.presentation.data

data class AroundMusicDto(
    val registeredMusicId: Int = 0,
    val subject: String = "",
    val singer: String = "",
    val albumImg: String = "",
    val occasionName: List<String> = listOf(),
)
