package com.example.composegenapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Rocket
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composegenapp.common.Constants.Companion.HOME_SCREEN
import com.example.composegenapp.common.Constants.Companion.LOGIN_SCREEN
import com.example.composegenapp.common.Constants.Companion.ROCKETS_SCREEN
import com.example.composegenapp.data.FalconInfo
import com.example.composegenapp.data.SampleData
import com.example.composegenapp.ui.FalconInfoCard
import com.example.composegenapp.ui.FalconInfoItemsScreen
import com.example.composegenapp.ui.HomeScreen
import com.example.composegenapp.ui.LoginScreen
import com.example.composegenapp.ui.theme.ComposeGenAppTheme
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tabItems = initTabItems()

        setContent {


            ComposeGenAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var selectedTabIndex by remember {
                        mutableIntStateOf(LOGIN_TAB)
                    }

                    val pagerState = rememberPagerState {
                        tabItems.size
                    }

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
                        composable (HOME_SCREEN) { HomeScreen() }
                        composable (ROCKETS_SCREEN) { FalconInfoItemsScreen() }
                        composable (LOGIN_SCREEN) { LoginScreen() }
                    }



                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .weight(1f)
                        )
                        { index ->
                            Log.d(">>>>", "index = $index")
                        }

                        TabRow(selectedTabIndex = selectedTabIndex) {
                            tabItems.forEachIndexed { index, item ->
                                Tab(selected = index == selectedTabIndex,
                                    onClick = {
                                        selectedTabIndex = index
                                    },
                                    text = {
                                        Text(item.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedTabIndex) {
                                                item.selectedItem
                                            } else {
                                                item.unselectedItem
                                            },
                                            contentDescription = item.title
                                        )
                                    })
                            }
                        }

                        LaunchedEffect(selectedTabIndex) {
                            pagerState.animateScrollToPage(selectedTabIndex)
                            Log.d("1>>>>", "$selectedTabIndex")
                        }

                        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                            if (!pagerState.isScrollInProgress) {
                                selectedTabIndex = pagerState.currentPage
                                Log.d("2>>>>", "$selectedTabIndex")
                                when (selectedTabIndex) {
                                    HOME_TAB -> navigate(navController, HOME_SCREEN)
                                    ROCKETS_TAB -> navigate(navController, ROCKETS_SCREEN)
                                    LOGIN_TAB -> navigate(navController, LOGIN_SCREEN)
                                }

                            }
                        }


                    }
                }
            }

        }
    }

    companion object {
        const val HOME_TAB = 0
        const val ROCKETS_TAB = 1
        const val LOGIN_TAB = 2

    }

}

fun navigate(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(0)
//        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
//        launchSingleTop = true
//        restoreState = true
    }
}



data class TabItem(
    val title: String,
    val unselectedItem: ImageVector,
    val selectedItem: ImageVector
)

private fun initTabItems(): List<TabItem> {
    return listOf(
        TabItem(
            title = "Home",
            unselectedItem = Icons.Outlined.Home,
            selectedItem = Icons.Filled.Home,

            ),
        TabItem(
            title = "Rockets",
            unselectedItem = Icons.Outlined.Rocket,
            selectedItem = Icons.Filled.Rocket
        ),
        TabItem(
            title = "Account",
            unselectedItem = Icons.Outlined.AccountCircle,
            selectedItem = Icons.Filled.AccountCircle
        )
    )
}

//-----------------------------------------------------------------------------------------------

@Composable
fun FalconInfoListView(falconInfos: List<FalconInfo>) {
    LazyColumn {
        items(falconInfos) { falconInfos ->
            FalconInfoCard(falconInfos)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposeGenAppTheme {
        FalconInfoListView(SampleData.getRockets())
    }
}
