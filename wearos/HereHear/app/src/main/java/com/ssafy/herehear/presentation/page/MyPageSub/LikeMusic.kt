package com.ssafy.herehear.presentation.page.MyPageSub

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ssafy.herehear.R
import com.ssafy.herehear.presentation.data.LikeMusicDto
import com.ssafy.herehear.presentation.retrofit.api.getLikeMusicList
import com.ssafy.herehear.presentation.retrofit.api.likeMusicListRequest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LikeMusic(
    personalCode: String
) {
    val likeMusicList = remember { mutableStateListOf(LikeMusicDto()) }
    val isFavoriteList = remember { mutableStateListOf(false) }

    LaunchedEffect(key1 = likeMusicList) {
        getLikeMusicList(personalCode, likeMusicList, isFavoriteList)
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
                Text(text = "좋아요 노래 목록")
            }
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }

        for ((index, likeMusic) in likeMusicList.withIndex()) {
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
                                    .data(likeMusic.albumImg)
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

                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // 노래 제목
                                Text(
                                    text = likeMusic.subject,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.basicMarquee()
                                )
                                // 가수
                                Text(
                                    text = likeMusic.singer,
                                    style = TextStyle(fontSize = 8.sp),
                                    modifier = Modifier.basicMarquee()
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                Image(
                                    painter = if (isFavoriteList[index]) painterResource(id = R.drawable.favorite) else painterResource(
                                        id = R.drawable.favorite_border
                                    ),
                                    contentDescription = "favorite",
                                    modifier = Modifier
                                        .width(16.dp)
                                        .height(16.dp)
                                        .align(Alignment.End)
                                        .clickable {
                                            likeMusicListRequest(
                                                personalCode,
                                                likeMusic.registeredMusicId,
                                                isFavoriteList,
                                                index
                                            )
                                        },
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