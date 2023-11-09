package com.ssafy.herehear.presentation.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.MutableState
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
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ssafy.herehear.R
import com.ssafy.herehear.presentation.retrofit.data.response.MusicDetailData

@Composable
fun MusicInfo(
    musicDetailData: MutableState<MusicDetailData>,
    navController: NavController,
    index: Int,
) {
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
//                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(musicDetailData.value.albumImg)
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
                                painter = painterResource(id = R.drawable.play_back),
                                contentDescription = "play_back",
                                modifier = Modifier.width(25.dp)
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
                                    text = musicDetailData.value.subject,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.DarkGray
                                    )
                                )
                                Spacer(modifier = Modifier.height(1.dp))
                                Text(
                                    text = musicDetailData.value.singer,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = Color.DarkGray,
                                        fontFamily = FontFamily.SansSerif
                                    )
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.play_forward),
                                contentDescription = "play_forward",
                                modifier = Modifier.width(25.dp),
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
        }

        item {
            Row {
                for (tag in musicDetailData.value.occasionName) {
                    Text(text = "#$tag")
                    Spacer(modifier = Modifier.width(3.dp))
                }
            }
        }

        item {
            AppCard(
                onClick = { },
                appName = {
                    Text(
                        musicDetailData.value.nickname,
                        style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    )
                },
                title = { },
                time = {
                    Text(
                        text = musicDetailData.value.createTime,
                        style = TextStyle(fontSize = 7.sp, fontWeight = FontWeight.Bold)
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            ) {
                Text(
                    text = musicDetailData.value.comment,
                    style = TextStyle(fontSize = 10.sp)
                )
            }
        }

        item {
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "좋아요 버튼")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "음악 링크")
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
