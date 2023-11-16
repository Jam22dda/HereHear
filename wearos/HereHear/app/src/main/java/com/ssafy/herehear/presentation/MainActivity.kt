/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.ssafy.herehear.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.ssafy.herehear.R
import com.ssafy.herehear.presentation.data.AroundMusicDto
import com.ssafy.herehear.presentation.data.GpsDto
import com.ssafy.herehear.presentation.data.MusicInfoDto
import com.ssafy.herehear.presentation.page.Landing
import com.ssafy.herehear.presentation.page.MainMap
import com.ssafy.herehear.presentation.page.MusicList
import com.ssafy.herehear.presentation.page.MyPage
import com.ssafy.herehear.presentation.page.MyPageSub.Follower
import com.ssafy.herehear.presentation.page.MyPageSub.Following
import com.ssafy.herehear.presentation.page.MyPageSub.LikeMusic
import com.ssafy.herehear.presentation.page.MyPageSub.RecentMusic
import com.ssafy.herehear.presentation.page.NoMusicAround
import com.ssafy.herehear.presentation.page.RouteType
import com.ssafy.herehear.presentation.retrofit.api.aroundMusicRequest
import com.ssafy.herehear.presentation.retrofit.api.authRequest
import com.ssafy.herehear.presentation.retrofit.api.musicListRequest
import com.ssafy.herehear.presentation.util.deletePersonalCodeFile
import com.ssafy.herehear.presentation.util.getCurrentLocation
import com.ssafy.herehear.presentation.util.readPersonalCodeFile
import com.ssafy.herehear.presentation.util.writePersonalCodeFile
import java.util.Calendar


class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, FLAG_MUTABLE)

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            600000, // 10분을 밀리초로 표현
            pendingIntent
        )

        setContent {
//            val c: Calendar = Calendar.getInstance()
//            c.set(Calendar.HOUR_OF_DAY, 3)
//            c.set(Calendar.MINUTE, 18)
//            c.set(Calendar.SECOND, 0)
//
//            val alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//            val intent = Intent(this, AlertReceiver::class.java)
//            val pendingIntent: PendingIntent =
//                PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)
//
//            if (c.before(Calendar.getInstance())) {
//                c.add(Calendar.DATE, 1);
//            }
//
//            alarmManager.setRepeating(
//                AlarmManager.RTC_WAKEUP,
//                c.timeInMillis,
//                300000,
//                pendingIntent)

            // 네비게이션 이동을 위한 nav controller
            navController = rememberSwipeDismissableNavController()

            // 앱이 켜질 때 로그인 처리 로직 -- start
            val personalCode =
                remember { mutableStateOf(baseContext.readPersonalCodeFile("personalCode.txt")) }
            val isLoginSuccess = remember { mutableStateOf("") }
            // 앱이 켜질 때 로그인 처리 로직 -- end

            val mainActivity: MainActivity = this

            // 라우팅 경로 등록 -- start
            SwipeDismissableNavHost(
                navController = navController,
                startDestination = RouteType.LOADING.toString()
            ) {
                composable(RouteType.LOADING.toString()) {
                    if (isLoginSuccess.value == "") {
                        authRequest(
                            personalCode.value,
                            isLoginSuccess,
                            navController,
                            RouteType.MAP.toString()
                        )
                    }

                    if (isLoginSuccess.value == "success") {
                        Log.d("[Loading]", "isLoginSuccess.value == success")
                        navController.navigate(RouteType.MAP.toString()) {
                            popUpTo(0) { inclusive = true }
                        }
                    } else if (isLoginSuccess.value == "fail") {
                        Log.d("[Loading]", "isLoginSuccess.value == fail")
                        navController.navigate(RouteType.LANDING.toString()) {
                            popUpTo(0) { inclusive = true }
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hoyoyo_box),
                            contentDescription = "App Logo",
                            modifier = Modifier
                                .fillMaxSize(0.7f)
                                .padding(bottom = 10.dp),
                        )
                    }
                }

                composable(RouteType.LANDING.toString()) {
                    // 로그인 처리 로직 -- start
                    Log.d("[MainActivity - Landing] personalCode", personalCode.value)
                    authRequest(
                        personalCode.value,
                        isLoginSuccess,
                        navController,
                        RouteType.MAP.toString()
                    )
                    // 로그인 처리 로직 -- end


                    // 랜딩 페이지 UI 컴포즈 -- start
                    Landing(personalCode)
                    // 랜딩 페이지 UI 컴포즈 -- end
                }

                composable(RouteType.MAP.toString()) {
                    // 로그인 성공시 파일에 코드 저장 이후 재시작 -- start
                    if (isLoginSuccess.value == "success") {
                        Log.d("[MainActivity - Map] code file update", personalCode.value)
                        isLoginSuccess.value = ""
                        baseContext.deletePersonalCodeFile("personalCode.txt")
                        baseContext.writePersonalCodeFile("personalCode.txt", personalCode.value)
                    }
                    // 로그인 성공시 파일에 코드 저장 이후 재시작 -- end


                    // 현재 gps 위치 가져오기
                    val gpsState = remember { mutableStateOf(GpsDto()) }
                    getCurrentLocation(mainActivity, gpsState)

                    // 음악 리스트 조회 후 Map 으로 이동
                    val musicList = remember { mutableStateListOf(MusicInfoDto()) }
                    if (gpsState.value.latitude != 0.0 && gpsState.value.longitude != 0.0) {
                        musicListRequest(personalCode.value, musicList)
                        MainMap(gpsState, musicList, navController)
                    }
                }

                composable(RouteType.MUSIC_LIST.toString()) {
                    val aroundMusicList = remember { mutableStateListOf(AroundMusicDto()) }

                    // 현재 gps 위치 가져오기
                    val gpsState = remember { mutableStateOf(GpsDto()) }
                    LaunchedEffect(key1 = gpsState) {
                        getCurrentLocation(mainActivity, gpsState)
                    }

                    if (gpsState.value.latitude != 0.0 && gpsState.value.longitude != 0.0) {
                        Log.d("[GPS TEST]", gpsState.value.toString())
                        LaunchedEffect(key1 = aroundMusicList, key2 = gpsState) {
                            aroundMusicRequest(
                                personalCode.value,
                                gpsState.value.latitude,
                                gpsState.value.longitude,
                                aroundMusicList
                            )
                        }

                        if (aroundMusicList.isEmpty()) {
                            navController.navigate(RouteType.NO_MUSIC.toString()) {
                                popUpTo(RouteType.MUSIC_LIST.toString()) { inclusive = true }
                                launchSingleTop = true
                            }
                        } else if (aroundMusicList.isNotEmpty()) {
                            Log.d("[MusicList 렌더링", aroundMusicList.toString())
                            MusicList(
                                personalCode = personalCode.value,
                                aroundMusicList = aroundMusicList.toList(),
                                navController = navController
                            )
                        }
                    }
                }

                composable(RouteType.NO_MUSIC.toString()) {
                    NoMusicAround(around = 500)
                }

                composable(RouteType.MY_PAGE.toString()) {
                    MyPage(personalCode.value, navController)
                }

                composable(RouteType.FOLLOWING.toString()) {
                    Following(personalCode.value)
                }

                composable(RouteType.FOLLOWER.toString()) {
                    Follower(personalCode.value)
                }

                composable(RouteType.RECENT_MUSIC.toString()) {
                    RecentMusic(personalCode.value)
                }

                composable(RouteType.LIKE_MUSIC.toString()) {
                    LikeMusic(personalCode.value)
                }

                composable(RouteType.ACHIEVEMENT.toString()) {

                }

                composable(RouteType.SPOTIFY.toString()) {
                    val intent =
                        this@MainActivity.packageManager.getLaunchIntentForPackage("com.spotify.music")
                    intent?.data = Uri.parse("spotify:track:3yS7jHZ8z5RpGnSASUZmGg")
                    intent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    this@MainActivity.startActivity(intent)
                }

                composable(RouteType.LOGOUT.toString()) {
                    personalCode.value = "";
                    baseContext.deletePersonalCodeFile("personalCode.txt")
                    navController.navigate(RouteType.LANDING.toString()) {
                        popUpTo(0) { inclusive = true }
                    }
                }

            }
            // 라우팅 경로 등록 -- end
        }
    }

}