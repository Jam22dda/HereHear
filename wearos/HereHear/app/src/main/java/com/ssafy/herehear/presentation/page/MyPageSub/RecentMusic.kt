package com.ssafy.herehear.presentation.page.MyPageSub

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ssafy.herehear.presentation.data.RecentMusicDto
import com.ssafy.herehear.presentation.retrofit.api.getRecentMusicList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecentMusic(
    personalCode: String
) {
    val recentMusicList = remember { mutableStateListOf(RecentMusicDto()) }

    LaunchedEffect(key1 = recentMusicList) {
        getRecentMusicList(personalCode, recentMusicList)
    }

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "최근 들은 노래")
            }
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }

        for (recentMusic in recentMusicList) {
            item {
                Spacer(modifier = Modifier.height(7.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .height(45.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth(0.75f)
                    ) {
                        Row {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(recentMusic.albumImg)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Column {
                                // 노래 제목
                                Text(
                                    text = recentMusic.subject,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.basicMarquee()
                                )
                                // 가수
                                Text(
                                    text = recentMusic.singer,
                                    style = TextStyle(fontSize = 8.sp),
                                    modifier = Modifier.basicMarquee()
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

}