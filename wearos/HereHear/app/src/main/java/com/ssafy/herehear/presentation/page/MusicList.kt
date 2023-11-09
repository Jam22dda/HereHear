package com.ssafy.herehear.presentation.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ssafy.herehear.presentation.data.AroundMusicDto
import com.ssafy.herehear.presentation.retrofit.api.musicDetailRequest
import com.ssafy.herehear.presentation.data.MusicDetailDto

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicList(
    personalCode: String,
    aroundMusicList: SnapshotStateList<AroundMusicDto>,
    navController: NavController
) {
    // music list 인덱스의 변화를 감지하는 상태
    val musicDetailList = remember { mutableStateListOf(MusicDetailDto()) }

    for (item in aroundMusicList) {
        musicDetailRequest(
            personalCode,
            item.registeredMusicId,
            musicDetailList
        )
    }

    HorizontalPager(
        pageCount = musicDetailList.size,
        modifier = Modifier.fillMaxSize(),
    ) { page ->
        MusicInfo(musicDetailList[page], navController)
    }
}