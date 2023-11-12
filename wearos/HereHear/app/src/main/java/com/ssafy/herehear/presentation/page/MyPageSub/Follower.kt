package com.ssafy.herehear.presentation.page.MyPageSub

import android.util.Log
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ssafy.herehear.presentation.data.FollowerDto
import com.ssafy.herehear.presentation.retrofit.api.getFollowerList

@Composable
fun Follower(
    personalCode: String,
) {
    val followerList = remember { mutableStateListOf(FollowerDto()) }

    LaunchedEffect(key1 = followerList) {
        getFollowerList(personalCode, followerList)
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
                Text(text = "Follower")
            }
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }

        for (following in followerList) {
            item {
                Spacer(modifier = Modifier.height(3.dp))
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        Row {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(following.profileCharacter?.characterImage)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .clip(CircleShape) // 모서리 클립 (필요시 다른 형태로 변경 가능)
                                    .width(25.dp)
                                    .height(25.dp)
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Column {
                                // 닉네임
                                Text(
                                    text = following.nickname,
                                    style = TextStyle(fontSize = 8.sp)
                                )
                                // 칭호
                                following.achievement?.title?.let {
                                    Text(
                                        text = it.titleName,
                                        style = TextStyle(fontSize = 6.sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}