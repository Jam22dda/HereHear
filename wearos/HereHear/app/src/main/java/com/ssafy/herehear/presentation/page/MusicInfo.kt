package com.ssafy.herehear.presentation.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ssafy.herehear.R
import com.ssafy.herehear.presentation.data.MusicDetailDto
import com.ssafy.herehear.presentation.retrofit.api.likeMusicRequest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicInfo(musicDetailDto: MusicDetailDto, personalCode: String, navController: NavController) {
    val isFavorite = remember {
        mutableStateOf(musicDetailDto.like)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(musicDetailDto.albumImg)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .clip(CircleShape) // 모서리 클립 (필요시 다른 형태로 변경 가능)
                        .alpha(0.6f)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.White.copy(alpha = 0.55f))
                        .align(Alignment.Center),
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_map),
                                contentDescription = "to map",
                                modifier = Modifier
                                    .width(25.dp)
                                    .clickable {
                                        navController.navigate(RouteType.MAP.toString()) {
                                            popUpTo(0) { inclusive = true }
                                        }
                                    }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(4f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = musicDetailDto.subject,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    ),
                                    modifier = Modifier.basicMarquee()
                                )
                                Spacer(modifier = Modifier.height(1.dp))
                                Text(
                                    text = musicDetailDto.singer,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = Color.DarkGray,
                                        fontFamily = FontFamily.SansSerif
                                    ),
                                    modifier = Modifier.basicMarquee()
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.play_forward),
//                                contentDescription = "play_forward",
//                                modifier = Modifier.width(25.dp),
//                            )
                            Image(
                                painter = if (isFavorite.value) painterResource(id = R.drawable.favorite) else painterResource(
                                    id = R.drawable.favorite_border
                                ),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .width(25.dp)
                                    .clickable {
                                        likeMusicRequest(
                                            personalCode,
                                            musicDetailDto.registeredMusicId,
                                            isFavorite
                                        )
                                    },
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Row {
                for (tag in musicDetailDto.occasionName) {
                    Box(
                        modifier = Modifier
                            .background(Color.DarkGray, RoundedCornerShape(5.dp))
                            .padding(3.dp),
                    ) {
                        Text(
                            text = "$tag",
                            style = TextStyle(fontSize = 11.sp, color = Color.LightGray)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        item {
            AppCard(
                onClick = { },
                appName = {
                    Text(
                        musicDetailDto.nickname,
                        style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    )
                },
                title = { },
                time = {
                    Text(
                        text = musicDetailDto.createTime,
                        style = TextStyle(fontSize = 7.sp, fontWeight = FontWeight.Bold)
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            ) {
                Text(
                    text = musicDetailDto.comment,
                    style = TextStyle(fontSize = 10.sp)
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    onClick = { navController.navigate(RouteType.SPOTIFY.toString()) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.play_circle),
                            contentDescription = "play",
                            modifier = Modifier
                                .height(16.dp)
                                .width(16.dp)
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = "음악 재생하기",
                            style = TextStyle(fontSize = 10.sp)
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
