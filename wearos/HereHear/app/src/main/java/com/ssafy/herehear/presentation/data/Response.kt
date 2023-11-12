package com.ssafy.herehear.presentation.data

// 인증 response
data class AuthResponse(
    val code: String,
    val message: String,
    val data: AuthDto
)

// 음악 리스트 response
data class MusicListResponse(
    val code: String,
    val message: String,
    val data: List<MusicInfoDto>
)

// 음악 상세 response
data class MusicDetailResponse(
    val code: String,
    val message: String,
    val data: MusicDetailDto
)

// 주변 음악 조회 response
data class AroundMusicListResponse(
    val code: String,
    val message: String,
    val data: List<AroundMusicDto>
)

// 멤머 정보 조회
data class MemberInfoResponse(
    val code: String,
    val message: String,
    val data: MemberInfoDto
)

// 업적 정보 조회
data class AchievementResponse(
    val code: String,
    val message: String,
    val data: AchievementDto
)