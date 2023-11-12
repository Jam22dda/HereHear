package com.ssafy.herehear.presentation.page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.wear.compose.material.Card
import com.ssafy.herehear.presentation.data.AchievementDto
import com.ssafy.herehear.presentation.data.MemberInfoDto
import com.ssafy.herehear.presentation.retrofit.api.getAchievement
import com.ssafy.herehear.presentation.retrofit.api.getMyProfile

@Composable
fun MyPage(
    personalCode: String,
) {
    val myProfile = remember { mutableStateOf(MemberInfoDto()) }
    val achievement = remember { mutableStateOf(AchievementDto()) }

    LaunchedEffect(key1 = myProfile) {
        getMyProfile(personalCode, myProfile)
        if (myProfile.value.achievementId != 0) {
            getAchievement(personalCode, myProfile.value.achievementId, achievement)
        }
    }

    LazyColumn() {
        item {
            // 칭호, 닉네임 등 표시
            Card(onClick = { /*TODO*/ }) {

            }
        }

        item {
            // 팔로워 목록, 팔로잉 목록
        }

        item {
            // 노래 들은 목록
        }

        item {
            // 좋아요한 목록
        }

        item {
            // 뱃지
        }
    }
}