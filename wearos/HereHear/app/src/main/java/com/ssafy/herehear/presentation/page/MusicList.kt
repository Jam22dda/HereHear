package com.ssafy.herehear.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.ssafy.herehear.presentation.retrofit.api.musicDetailRequest
import com.ssafy.herehear.presentation.retrofit.data.response.MusicAroundData
import com.ssafy.herehear.presentation.retrofit.data.response.MusicDetailData

@Composable
fun MusicList(
    personalCode: String,
    musicInfoList: List<MusicAroundData>,
    navController: NavController
) {
    // music list 인덱스의 변화를 감지하는 상태
    val index by remember { mutableStateOf(0) }
    val musicDetail = remember { mutableStateOf(MusicDetailData()) }

    musicDetailRequest(personalCode, musicInfoList[index].registeredMusicId, musicDetail)

    MusicInfo(musicDetail, navController, index)
}