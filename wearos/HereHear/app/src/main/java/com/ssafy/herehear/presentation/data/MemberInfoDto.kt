package com.ssafy.herehear.presentation.data

data class MemberInfoDto(
    val nickname: String = "",
    val profileCharacter: ProfileCharacterDto = ProfileCharacterDto(),
    val achievementId: Int = 0
)

data class ProfileCharacterDto(
    val profileCharacterId: Int = 0,
    val characterName: String = "",
    val characterImage: String = ""
)