package com.ssafy.herehear.presentation.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.ssafy.herehear.R

@Composable
fun Landing(personalCode: MutableState<String>) {
    // 사용자가 입력한 code 저장을 위한 state
    val (inputCode, setInputCode) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        Image(
            painter = painterResource(id = R.drawable.hoyoyo_line),
            contentDescription = "HereHear_logo",
            modifier = Modifier.fillMaxWidth(0.6f)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Personal Code 를 입력해주세요",
            style = TextStyle(
                fontSize = 12.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = inputCode,
            onValueChange = setInputCode,
            modifier = Modifier
                .background(color = Color.DarkGray)
                .height(25.dp),
        )

        Button(
            onClick = {
                Log.d("[LandingPage] inputCode 변경", inputCode.toString())
                personalCode.value = inputCode
                Log.d("[LandingPage] personalCode", personalCode.value.toString())
            },
            modifier = Modifier
                .padding(all = 8.dp)
                .clip(RoundedCornerShape(1888.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent, // 버튼의 배경색을 설정합니다.
                contentColor = Color.White // 버튼의 내용(텍스트, 아이콘)의 색상을 설정합니다.
            )
        ) {
            Text(text = "제출하기", style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold))
        }
    }
}