package com.ssafy.herehear.presentation.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MusicInfoScreen(albumImage: Painter, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize(0.62f)
            ) {
                Image(
                    painter = albumImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)) // 모서리 클립 (필요시 다른 형태로 변경 가능)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
        }


        item {
            Text(
                text = "Ditto",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "New Jeans",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 12.sp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            val context = LocalContext.current
            AppCard(
                onClick = { },
                appName = {
                    Text(
                        "작성자",
                        style = TextStyle(fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    )
                },
                title = { },
                time = {
                    Text(
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                        style = TextStyle(fontSize = 7.sp)
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 30.dp, end = 30.dp, bottom = 30.dp)
            ) {
                Text(
                    text = "노래 정말 좋습니다!!!!\n노래 정말 좋습니다!!!!\n노래 정말 좋습니다!!!!",
                    style = TextStyle(fontSize = 10.sp)
                )
            }
        }
    }
}