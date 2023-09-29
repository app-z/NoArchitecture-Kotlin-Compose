package com.example.composegenapp.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController
import com.example.composegenapp.common.Constants
import com.example.composegenapp.common.Constants.Companion.HOME_TAB
import com.example.composegenapp.common.Constants.Companion.LOGIN_TAB
import com.example.composegenapp.common.Constants.Companion.ROCKETS_TAB

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomTabPanel(navController: NavController) {

    val tabItems = initTabItems()

    var selectedTabIndex by remember {
        mutableIntStateOf(LOGIN_TAB)
    }

    val pagerState = rememberPagerState {
        tabItems.size
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
                    HOME_TAB -> navigate(navController, Constants.HOME_SCREEN)
                    ROCKETS_TAB -> navigate(navController, Constants.ROCKETS_SCREEN)
                    LOGIN_TAB -> navigate(navController, Constants.LOGIN_SCREEN)
                }

            }
        }
    }
}

fun navigate(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(0)
    }
}

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


data class TabItem(
    val title: String,
    val unselectedItem: ImageVector,
    val selectedItem: ImageVector
)

