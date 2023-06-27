package com.nsicyber.travelandshare.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nsicyber.travelandshare.AuthActivity
import com.nsicyber.travelandshare.models.TravelDataModel
import com.nsicyber.travelandshare.viewmodel.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SplashScreen(
) {

    var viewModel = hiltViewModel<SplashViewModel>()
    var context=LocalContext.current
    viewModel.startSplash {
        context.startActivity(Intent(context, AuthActivity::class.java))

    }
}


