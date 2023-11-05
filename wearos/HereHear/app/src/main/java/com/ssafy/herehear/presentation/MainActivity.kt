/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.ssafy.herehear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavOptions
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.ssafy.herehear.R
import com.ssafy.herehear.presentation.page.GoogleMapPage
import com.ssafy.herehear.presentation.page.LandingPage
import com.ssafy.herehear.presentation.page.MusicInfoScreen
import com.ssafy.herehear.presentation.retrofit.api.authRequest
import com.ssafy.herehear.presentation.util.readPersonalCodeFile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberSwipeDismissableNavController()
            var startDestination: String = "landing"

            // access 처리
            val personalCode: String = baseContext.readPersonalCodeFile("personalCode.txt")
            if (personalCode != "") {
                val authRequest = authRequest(personalCode)
                if (authRequest?.accessResult == true) {
                    startDestination = "map"
                }
            }

            SwipeDismissableNavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable("landing") {
                    LandingPage(navController, baseContext)
                }
                composable("map") {
                    GoogleMapPage(navController)
                }
                composable("musicInfo") {
                    val albumImage = painterResource(R.drawable.ditto)
                    MusicInfoScreen(albumImage = albumImage, navController)
                }
            }
        }
    }
}