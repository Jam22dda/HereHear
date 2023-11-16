package com.ssafy.herehear.presentation.util

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.ssafy.herehear.presentation.MainActivity
import com.ssafy.herehear.presentation.data.GpsDto

fun getCurrentLocation(
    mainActivity: MainActivity,
    gpsState: MutableState<GpsDto>,
) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(mainActivity)
    if (ActivityCompat.checkSelfPermission(
            mainActivity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            mainActivity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            mainActivity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            200 // 적절한 요청 코드 상수
        )
    }

//            권한 요청
    fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
        .addOnSuccessListener { location: Location? ->
            location?.let {
                Log.d(
                    "[getCurrentLocation]",
                    "latitude: ${location.latitude}, longitude: ${location.longitude}"
                )
                gpsState.value = GpsDto(location.latitude, location.longitude)
            }
        }
        .addOnFailureListener { e ->
            Log.d("CURRENT GPS Location", "Failed to get current location: ${e.message}")
        }
}
