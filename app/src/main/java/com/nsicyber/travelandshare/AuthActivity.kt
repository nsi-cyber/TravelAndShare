package com.nsicyber.travelandshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nsicyber.travelandshare.screens.GalleryScreen
import com.nsicyber.travelandshare.screens.LoginScreen
import com.nsicyber.travelandshare.screens.SignupScreen
import com.nsicyber.travelandshare.ui.theme.TravelAndShareTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAndShareTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login_screen") {

                    composable("login_screen") {
                        LoginScreen(navController = navController)
                    }


                    composable("singup_screen") {
                        SignupScreen(navController = navController)
                    }


                }


            }
        }
    }
}