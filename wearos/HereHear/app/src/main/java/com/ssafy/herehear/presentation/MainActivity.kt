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
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.ssafy.herehear.R
import com.ssafy.herehear.presentation.page.GoogleMapPage
import com.ssafy.herehear.presentation.page.LandingPage
import com.ssafy.herehear.presentation.page.MusicInfoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberSwipeDismissableNavController()

            SwipeDismissableNavHost(
                navController = navController,
                startDestination = "landing"
            ) {
                composable("landing") {
                    LandingPage(navController)
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