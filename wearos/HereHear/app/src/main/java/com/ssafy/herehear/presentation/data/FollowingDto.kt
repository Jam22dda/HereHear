package com.ssafy.herehear.presentation.data

data class FollowingDto(
    val memberId: Int = 0,
    val nickname: String = "",
    val profileCharacter: ProfileCharacterDto? = ProfileCharacterDto(),
    val achievement: AchievementDto? = AchievementDto(),
    val isFollowed: Boolean = false
)
