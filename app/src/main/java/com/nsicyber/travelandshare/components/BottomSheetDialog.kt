package com.nsicyber.travelandshare.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nsicyber.travelandshare.models.TravelDataModel
import com.nsicyber.travelandshare.viewmodel.BottomSheetViewModel
import kotlinx.coroutines.launch


@Composable
fun BottomSheetDialog(navController: NavController, travelDataModel: TravelDataModel?) {
    travelDataModel?.let {

        var viewModel = hiltViewModel<BottomSheetViewModel>()

        var scope = rememberCoroutineScope();



        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            backgroundColor = Color.Gray,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = travelDataModel.title ?: "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(vertical = 14.dp)
                )
                LazyRow(contentPadding = PaddingValues(16.dp)) {

                    items(travelDataModel.images.size) {
                        Box(Modifier.clickable {
                            navController.navigate("galleryScreen")
                        }) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(travelDataModel.images.get(it))
                                    .crossfade(true)
                                    .build(),
                                // placeholder = painterResource(R.drawable.deezer_logo),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }

                    }
                }



                Row(
                    modifier = Modifier
                        .size(180.dp)
                        .padding(top = 24.dp),
                ) {

                    Button(onClick = {
                        navController.navigate("travelDetail")
                    }) {
                        Text(text = "Detaya Git")
                    }

                    travelDataModel.gps?.let {
                        Button(onClick = {
                            viewModel.openMaps(travelDataModel.gps)
                        }) {
                            Text(text = "Konuma Git")
                        }
                    }


                }


            }
        }
    }
}