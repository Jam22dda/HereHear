/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.ssafy.herehear.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.ssafy.herehear.presentation.data.GpsDto
import com.ssafy.herehear.presentation.page.Landing
import com.ssafy.herehear.presentation.page.MainMap
import com.ssafy.herehear.presentation.page.RouteType
import com.ssafy.herehear.presentation.retrofit.api.authRequest
import com.ssafy.herehear.presentation.util.deletePersonalCodeFile
import com.ssafy.herehear.presentation.util.getCurrentLocation
import com.ssafy.herehear.presentation.util.readPersonalCodeFile
import com.ssafy.herehear.presentation.util.writePersonalCodeFile


class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                    Log.d("[MainActivity] personalCode", personalCode.value)
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
                        Log.d("[MainActivity] code file update", personalCode.value)
                        isLoginSuccess.value = ""
                        baseContext.deletePersonalCodeFile("personalCode.txt")
                        baseContext.writePersonalCodeFile("personalCode.txt", personalCode.value)
                    }
                    // 로그인 성공시 파일에 코드 저장 이후 재시작 -- end


                    // 1) 현재 위치 가져오기
                    val gpsState = remember { mutableStateOf(GpsDto()) }
                    getCurrentLocation(mainActivity, gpsState)

                    // 2) 현재의 모든 음악 뽑아오기
                    if (gpsState.value.latitude != 0.0 && gpsState.value.longitude != 0.0) {
                        MainMap(gpsState, navController)
                    }

                    // 3) map 의 매개변수로 전달


//                    val drawableId: Int =
//                        R.drawable.free_icon_rec_1783356 // 'your_drawable_name'은 drawable 폴더에 있는 이미지 이름

//                    val bitmapDrawable =
//                        ContextCompat.getDrawable(baseContext, drawableId) as BitmapDrawable

//                    val bitmap = bitmapDrawable.bitmap
//                    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 30, 30, false)

//                    MainMap(navController, mainActivity, baseContext, resizedBitmap)
                }

                composable(RouteType.MUSIC_LIST.toString()) {

                }
            }
            // 라우팅 경로 등록 -- end
        }
    }
}