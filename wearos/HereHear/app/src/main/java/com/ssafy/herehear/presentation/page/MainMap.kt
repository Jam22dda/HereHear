package com.ssafy.herehear.presentation.page

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ssafy.herehear.R
import com.ssafy.herehear.presentation.data.GpsDto
import com.ssafy.herehear.presentation.data.MusicInfoDto

@Composable
fun MainMap(
    gpsState: MutableState<GpsDto>,
    musicList: SnapshotStateList<MusicInfoDto>,
    navController: NavHostController,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(gpsState.value.latitude, gpsState.value.longitude),
            15f
        )
    }

    // lat/lng 가 변경될 때 카메라 위치 업데이트
    LaunchedEffect(gpsState) {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    gpsState.value.latitude,
                    gpsState.value.longitude
                ), 12f
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
//        uiSettings = MapUiSettings(zoomControlsEnabled = false, zoomGesturesEnabled = true),
        ) {
            // 마커 이미지 로드
            val myBitmap = getBitmapMarker(R.drawable.my_location, 30, 30)
            val musicBitmap = getBitmapMarker(R.drawable.music_marker, 30, 30)
            val testMarker =
                getBitmapMarker(imageInt = R.drawable.icon_herehear, dstWidth = 37, dstHeight = 42)

            // 현재 위치 마커 표시
            Marker(
                state = MarkerState(
                    LatLng(
                        gpsState.value.latitude,
                        gpsState.value.longitude
                    )
                ),
                icon = myBitmap?.let { BitmapDescriptorFactory.fromBitmap(it) },
                anchor = Offset(0.5f, 0.5f)
            )

            // 음악 리스트 마커 표시
            for (music in musicList) {
                Marker(
                    state = MarkerState(LatLng(music.lat, music.lng)),
                    icon = testMarker?.let { BitmapDescriptorFactory.fromBitmap(it) },
                )
            }

            // 현재 위치 기준 반경 500미터 원 그리기
            Circle(
                center = LatLng(
                    gpsState.value.latitude,
                    gpsState.value.longitude
                ),
                radius = 2000.0, // 원의 반경을 미터 단위로 설정
                fillColor = Color.Blue.copy(alpha = 0.1f), // 원 안쪽 색상과 투명도
                strokeColor = Color.Blue, // 원 테두리 색상
                strokeWidth = 1f, // 원 테두리 굵기
            )
        }

        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth(0.6f)
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            // list 버튼 클릭시 버튼을 진하게 변경하는 이벤트 -- start
            val interactionSource = remember { MutableInteractionSource() }
            val alpha = remember { mutableStateOf(0.9f) }

            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect { interaction ->
                    when (interaction) {
                        is PressInteraction.Press -> alpha.value = 1.0f
                        is PressInteraction.Release -> alpha.value = 0.8f
                        is PressInteraction.Cancel -> alpha.value = 0.8f
                    }
                }
            }
            // list 버튼 클릭시 버튼을 진하게 변경하는 이벤트 -- end


            // list 버튼 이미지 -- start
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_playlist),
                    contentDescription = "list_btn",
                    alpha = alpha.value,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
//                        .align(Alignment.Center)
                        .clickable(
                            indication = null,  // 클릭 시 시각적 피드백 없음
                            interactionSource = interactionSource
                        ) {
                            navController.navigate(RouteType.MUSIC_LIST.toString())
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.icon_setting),
                    contentDescription = "전체 메뉴",
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable(
                            indication = null,  // 클릭 시 시각적 피드백 없음
                            interactionSource = interactionSource
                        ) {
                            navController.navigate(RouteType.MY_PAGE.toString())
                        }
                )
            }
            // list 버튼 이미지 -- end
        }
    }
}

@Composable
fun getBitmapMarker(imageInt: Int, dstWidth: Int, dstHeight: Int): Bitmap? {
    // Drawable을 Bitmap으로 변환합니다.
    val drawable = ContextCompat.getDrawable(LocalContext.current, imageInt)

    val bitmap = if (drawable is BitmapDrawable) {
        Bitmap.createScaledBitmap(drawable.bitmap, dstWidth, dstHeight, true)
    } else {
        Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        ).also {
            val canvas = Canvas(it)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
        }
    }

    return bitmap
}