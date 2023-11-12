package com.ssafy.herehear.presentation.page

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ssafy.herehear.presentation.data.AroundMusicDto
import com.ssafy.herehear.presentation.retrofit.api.musicDetailRequest
import com.ssafy.herehear.presentation.data.MusicDetailDto

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicList(
    personalCode: String,
    aroundMusicList: List<AroundMusicDto>,
    navController: NavController
) {
    // music list 인덱스의 변화를 감지하는 상태
    val musicDetailList = remember { mutableStateListOf(MusicDetailDto()) }
    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = aroundMusicList) {
        musicDetailList.clear()
        for (item in aroundMusicList) {
            musicDetailRequest(
                personalCode,
                item.registeredMusicId,
                musicDetailList
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        pageCount = musicDetailList.size,
        modifier = Modifier.fillMaxSize(),
    ) { page ->
        MusicInfo(musicDetailList[page], personalCode, navController)
        LaunchedEffect(pagerState) {
            Log.d("[MusicList - Pager]", pagerState.currentPage.toString())
            // collectAsState를 사용하여 현재 페이지의 변화를 수집합니다.
            snapshotFlow { pagerState.currentPage }.collect { page ->
                if (page == 0 && pagerState.currentPageOffsetFraction < -0.85f) {
                    navController.navigate(RouteType.MAP.toString()) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }
}