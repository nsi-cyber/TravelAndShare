package com.nsicyber.travelandshare

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.nsicyber.travelandshare.components.BottomSheetDialog
import com.nsicyber.travelandshare.models.TravelDataModel
import com.nsicyber.travelandshare.screens.DetailScreen
import com.nsicyber.travelandshare.screens.GalleryScreen
import com.nsicyber.travelandshare.screens.ListScreen
import com.nsicyber.travelandshare.screens.MapScreen
import com.nsicyber.travelandshare.ui.theme.TravelAndShareTheme
import com.nsicyber.travelandshare.utils.parse
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAndShareTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main_screen") {

                    composable("main_screen") {
                        MainScreen(navController = navController)
                    }

                    composable(
                        "gallery_screen?index={index}&images={images}",
                        arguments = listOf(
                            navArgument("index") {
                                type = NavType.IntType
                            }, navArgument("images") {
                                type = NavType.StringArrayType
                            }
                        )
                    ) {

                        val index = remember {
                            it.arguments?.getInt("index")
                        }
                        val images = remember {
                            it.arguments?.getStringArrayList("images")
                        }

                        GalleryScreen(
                            navController = navController,
                            index,
                            images
                        )
                    }
                    composable(
                        "detail_screen?model={model}",
                        arguments = listOf(
                            navArgument("model") {
                                type = NavType.StringType
                            }
                        )
                    ) {

                        val model = remember {
                            it.arguments?.getString("model")
                        }
                        DetailScreen(
                            navController = navController,
                            parse<TravelDataModel>(model)
                        )
                    }


                }


            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MainScreen(navController: NavController) {

    val tabData = getTabList()
    val pagerState = rememberPagerState(pageCount = tabData.size)

    var travelDataModel by remember {
        mutableStateOf<TravelDataModel?>(null)
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()




    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "profile",
                        title = "Profil",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "settings",
                        title = "Ayarlar",
                        contentDescription = "Go to settings screen",
                        icon = Icons.Default.Settings
                    ),
                    MenuItem(
                        id = "exit",
                        title = "Çıkış Yap",
                        contentDescription = "Exit from application",
                        icon = Icons.Default.ExitToApp
                    ),
                ),
                onItemClick = {
                    println("Clicked on ${it.title}")
                }
            )
        }, content = {

            val modalBottomSheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden
            )

            var scope = rememberCoroutineScope();
            ModalBottomSheetLayout(

                sheetState = modalBottomSheetState,
                sheetShape = RoundedCornerShape(topEnd = 30.dp),
                sheetContent = {
                    BottomSheetDialog(navController, travelDataModel)
                },

                ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    TabLayout(tabData, pagerState)
                    TabContent(navController = navController, tabData, pagerState) {

                        scope.launch {
                            travelDataModel = it
                            modalBottomSheetState.show()
                        }

                    }
                }
            }
        })
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun TabLayout(tabData: List<Pair<String, ImageVector>>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()
    /* val tabColor = listOf(
         Color.Gray,
         Color.Yellow,
         Color.Blue,
         Color.Red
     )
 */
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier = Modifier.height(5.dp))
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 5.dp,
                color = Color.White
            )
        },


        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        tabData.forEachIndexed { index, s ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
                icon = {
                    Icon(imageVector = s.second, contentDescription = null)
                },
                text = {
                    Text(text = s.first)
                })
        }
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun TabContent(
    navController: NavController,
    tabData: List<Pair<String, ImageVector>>,
    pagerState: PagerState,
    completion: (model: TravelDataModel?) -> Unit

) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                ListScreen(navController = navController)
            }

            1 -> {
                MapScreen(navController = navController) {
                    completion(it)
                }
            }


        }

    }

}


fun getTabList(): List<Pair<String, ImageVector>> {
    return listOf(
        "Places" to Icons.Default.Home,
        "Map" to Icons.Default.Search,

        )
}


@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            androidx.compose.material.IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer"
                )
            }
        }
    )
}

data class MenuItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}