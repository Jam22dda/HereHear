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

// 팔로잉 목록 조회
data class FollowingResponse(
    val code: String,
    val message: String,
    val data: List<FollowingDto>
)

// 팔로워 목록 조회
data class FollowerResponse(
    val code: String,
    val message: String,
    val data: List<FollowerDto>
)

// 최근 들은 음악 조회
data class RecentMusicResponse(
    val code: String,
    val message: String,
    val data: List<RecentMusicDto>
)

// 좋아요한 음악
data class LikeMusicResponse(
    val code: String,
    val message: String,
    val data: List<LikeMusicDto>
)