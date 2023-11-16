package com.ssafy.herehear.presentation.data

data class AchievementDto(
    val achievementId: Int = 0,
    val mission: String = "",
    val badge: BadgeDto = BadgeDto(),
    val title: TitleDto = TitleDto()
)

data class BadgeDto(
    val badgeCode: Int = 0,
    val badgeName: String = "",
    val badgeImg: String = ""
)

data class TitleDto(
    val titleCode: Int = 0,
    val titleName: String = ""
)