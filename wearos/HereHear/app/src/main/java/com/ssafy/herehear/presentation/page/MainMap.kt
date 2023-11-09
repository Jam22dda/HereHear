package com.ssafy.herehear.presentation.page

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
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

@Composable
fun MainMap(
    gpsState: MutableState<GpsDto>,
    navController: NavHostController,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(gpsState.value.latitude, gpsState.value.longitude),
            15f
        )
    }

    // `LaunchedEffect`를 사용하여 lat/lng가 변경될 때 카메라 위치 업데이트
    LaunchedEffect(gpsState) {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    gpsState.value.latitude,
                    gpsState.value.longitude
                ), 15f
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
            val bitmap = getBitmapMarker()

            Marker(
                state = MarkerState(
                    LatLng(
                        gpsState.value.latitude,
                        gpsState.value.longitude
                    )
                ),
                icon = bitmap?.let { BitmapDescriptorFactory.fromBitmap(it) },
            )

            // 음악 마커
//            for (music in musicList) {
//                Marker(
//                    state = MarkerState(LatLng(music.lat, music.lng)),
//                    icon = BitmapDescriptorFactory.fromBitmap(bitmap),
//                )
//            }

            // 반경 500미터 원 그리기
            Circle(
                center = LatLng(
                    gpsState.value.latitude,
                    gpsState.value.longitude
                ),
                radius = 200.0, // 원의 반경을 미터 단위로 설정
                fillColor = Color.Blue.copy(alpha = 0.1f), // 원 안쪽 색상과 투명도
                strokeColor = Color.Blue, // 원 테두리 색상
                strokeWidth = 1f, // 원 테두리 굵기
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .align(Alignment.BottomCenter),
        ) {
            Button(
                onClick = { },
                modifier = Modifier.height(20.dp),
            ) {
                Text(text = "list")
            }
        }
    }
}

@Composable
fun getBitmapMarker(): Bitmap? {
    // 현재 위치 마커
    val drawable = ContextCompat.getDrawable(LocalContext.current, R.drawable.my_location)
    // Drawable을 Bitmap으로 변환합니다.
    val bitmap = if (drawable is BitmapDrawable) {
        drawable.bitmap
    } else {
        // 비트맵 드로어블이 아닌 경우, 비트맵을 생성합니다.
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