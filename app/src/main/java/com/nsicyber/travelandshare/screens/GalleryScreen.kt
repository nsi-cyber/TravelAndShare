package com.nsicyber.travelandshare.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryScreen(
    navController: NavController, index: Int?, images: ArrayList<String>?
) {
    val pagerState = rememberPagerState()

    var shiftedArr = shiftArray(images, index)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {

Box() {
    Box(modifier = Modifier.clickable {
        navController.popBackStack()
    }){    AsyncImage(model = "", contentDescription ="" )
    }

    HorizontalPager(
        pageCount = images!!.size,
        state = pagerState,
        modifier = Modifier
    ) { currentPage ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(model = shiftedArr[currentPage], contentDescription = "")
        }
    }
}


    }

}

fun shiftArray(arrayList: ArrayList<String>?, index: Int?): ArrayList<String> {
    if (arrayList == null || index == null || index !in 0 until arrayList.size) {
        return ArrayList()
    }

    val shiftedArray = ArrayList<String>(arrayList.size)

    for (i in index until arrayList.size) {
        shiftedArray.add(arrayList[i])
    }

    for (i in 0 until index) {
        shiftedArray.add(arrayList[i])
    }

    return shiftedArray
}
