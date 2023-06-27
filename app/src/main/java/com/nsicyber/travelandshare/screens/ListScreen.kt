package com.nsicyber.travelandshare.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nsicyber.travelandshare.models.TravelDataModel
import com.nsicyber.travelandshare.viewmodel.ListScreenViewModel

@Composable
fun ListScreen(
    navController: NavController
) {

    var viewModel = hiltViewModel<ListScreenViewModel>()

    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    val travelList by remember {
        viewModel.travelList
    }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {

        items(travelList.size) {
            ListItem(
                data = travelList.get(it)
            ) {
                navController.navigate("travel_detail?model=${it}")
            }
        }
    }

}

@Composable
fun ListItem(
    data: TravelDataModel?,
    completion: (model: TravelDataModel?) -> Unit
) {

    Box(modifier = Modifier.clickable { completion(data) }) {
        Text(text = data?.title.toString())
    }


}