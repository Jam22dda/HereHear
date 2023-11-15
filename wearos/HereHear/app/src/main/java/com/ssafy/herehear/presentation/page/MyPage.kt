package com.ssafy.herehear.presentation.page

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ssafy.herehear.presentation.data.AchievementDto
import com.ssafy.herehear.presentation.data.MemberInfoDto
import com.ssafy.herehear.presentation.retrofit.api.getAchievement
import com.ssafy.herehear.presentation.retrofit.api.getMyProfile
import com.ssafy.herehear.presentation.util.deletePersonalCodeFile

@Composable
fun MyPage(
    personalCode: String,
    navController: NavHostController,
) {
    val myProfile = remember { mutableStateOf(MemberInfoDto()) }
    val achievement = remember { mutableStateOf(AchievementDto()) }

    val cardHeight = 37.dp

    LaunchedEffect(key1 = myProfile) {
        getMyProfile(personalCode, myProfile)
    }
    if (myProfile.value.achievementId != 0) {
        getAchievement(personalCode, myProfile.value.achievementId, achievement)
    }

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            // 칭호, 닉네임 등 표시
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(achievement.value.badge.badgeImg)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(13.dp)
                            .height(13.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape) // 모서리 클립 (필요시 다른 형태로 변경 가능)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = achievement.value.title.titleName,
                        style = TextStyle(fontSize = 8.sp)
                    )
                }
                Text(
                    text = myProfile.value.nickname + " 님",
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            // 팔로워 목록, 팔로잉 목록
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                ) {
                    Card(
                        onClick = { navController.navigate(RouteType.FOLLOWING.toString()) },
                        modifier = Modifier
                            .weight(8f)
                            .height(cardHeight),
                    ) {
                        Text(
                            text = "Following",
                            style = TextStyle(fontSize = 9.sp),
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(3.dp)
                            .weight(1f)
                    )
                    Card(
                        onClick = { navController.navigate(RouteType.FOLLOWER.toString()) },
                        modifier = Modifier
                            .weight(8f)
                            .height(cardHeight),
                    ) {
                        Text(
                            text = "Follower",
                            style = TextStyle(fontSize = 9.sp)
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(7.dp))
        }

        item {
            // 노래 들은 목록
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    onClick = { navController.navigate(RouteType.RECENT_MUSIC.toString()) },
                    modifier = Modifier.fillMaxWidth(0.7f),
                ) {
                    Text(
                        text = "최근 들은 노래",
                        style = TextStyle(fontSize = 9.sp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(7.dp))
        }

        item {
            // 좋아요한 목록
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    onClick = { navController.navigate(RouteType.LIKE_MUSIC.toString()) },
                    modifier = Modifier.fillMaxWidth(0.7f),
                ) {
                    Text(
                        text = "좋아요한 노래",
                        style = TextStyle(fontSize = 9.sp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(7.dp))
        }

        item {
            // 뱃지
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    onClick = {
                        navController.navigate(RouteType.ACHIEVEMENT.toString())
                    },
                    modifier = Modifier.fillMaxWidth(0.7f),
                ) {
                    Text(
                        text = "업적 목록",
                        style = TextStyle(fontSize = 9.sp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            // 로그아웃
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    onClick = {
                        navController.navigate(RouteType.LOGOUT.toString()) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.7f),
                ) {
                    Text(
                        text = "로그아웃",
                        style = TextStyle(fontSize = 9.sp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}