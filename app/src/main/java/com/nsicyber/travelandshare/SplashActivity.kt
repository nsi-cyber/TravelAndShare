package com.nsicyber.travelandshare

import android.app.Notification
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.nsicyber.travelandshare.models.TravelDataModel
import com.nsicyber.travelandshare.screens.DeepDetailScreen
import com.nsicyber.travelandshare.screens.DetailScreen
import com.nsicyber.travelandshare.screens.GalleryScreen
import com.nsicyber.travelandshare.screens.SplashScreen
import com.nsicyber.travelandshare.ui.theme.TravelAndShareTheme
import com.nsicyber.travelandshare.utils.parse

class SplashActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAndShareTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen") {

                    composable("splash_screen") {
                        SplashScreen()
                    }

                    composable(
                        route="detail_deeplink?id={id}", deepLinks = listOf(navDeepLink {
                            uriPattern= "https://travelandshare.shared/{id}"
                            action= Intent.ACTION_VIEW
                        }),
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )
                    ) {

                        val id = remember {
                            it.arguments?.getInt("id")
                        }


                        DeepDetailScreen(
                            navController = navController,
                            id!!
                        )
                    }



                }


            }
        }
    }
}
