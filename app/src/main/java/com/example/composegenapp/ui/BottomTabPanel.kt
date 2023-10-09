package com.example.composegenapp.ui

import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composegenapp.R
import com.example.composegenapp.domain.usecase.GetFalconInfoUseCase
import com.example.composegenapp.ui.Screen.Companion.HOME_SCREEN
import com.example.composegenapp.ui.Screen.Companion.LOGIN_SCREEN
import com.example.composegenapp.ui.Screen.Companion.LOGIN_TAB
import com.example.composegenapp.ui.Screen.Companion.ROCKETS_SCREEN
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomTabPanel(getFalconInfoUseCase: GetFalconInfoUseCase,
                   navController: NavHostController)
{

    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
        composable(HOME_SCREEN) { HomeScreen() }
        composable(ROCKETS_SCREEN) { FalconInfoItemsScreen(getFalconInfoUseCase) }
        composable(LOGIN_SCREEN) { LoginScreen() }
    }

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
            Timber.d("index = $index")
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
            Timber.d("1> selectedTabIndex = $selectedTabIndex")
            navigateTo(navController, selectedTabIndex)
        }

        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
                Timber.d("2> $selectedTabIndex")
            }
        }
    }
}

private fun navigateTo(navController: NavController,  tabIndex: Int) {
    val item = listOf(Screen.Home, Screen.Rockets, Screen.Login)
        .first{ it.tabIndex == tabIndex }
    navController.navigate(item.route) {
        if (item.popUp)popUpTo(0)
    }
}

sealed class Screen(
    val tabIndex: Int,
    val route: String,
    @StringRes val textResId: Int,
    val selectedItem: ImageVector,
    val unselectedItem: ImageVector,
    val popUp: Boolean = true
) {
    data object Home : Screen(HOME_TAB, HOME_SCREEN, R.string.home,
        Icons.Filled.Home, Icons.Outlined.Home)
    data object Rockets : Screen(ROCKETS_TAB, ROCKETS_SCREEN, R.string.rockets,
        Icons.Filled.Rocket, Icons.Outlined.Rocket)
    data object Login : Screen(LOGIN_TAB, LOGIN_SCREEN, R.string.login,
        Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle)

    companion object {
        const val HOME_SCREEN = "homeScreen"
        const val ROCKETS_SCREEN = "rocketsScreen"
        const val LOGIN_SCREEN = "loginScreen"

        const val HOME_TAB = 0
        const val ROCKETS_TAB = 1
        const val LOGIN_TAB = 2
    }
}



@Composable
private fun initTabItems(): List<TabItem> {
    val items = listOf(Screen.Home, Screen.Rockets, Screen.Login)

    return items.map {
        TabItem(title = stringResource(it.textResId),
            unselectedItem = it.unselectedItem,
            selectedItem = it.selectedItem)
    }
}


data class TabItem(
    val title: String,
    val unselectedItem: ImageVector,
    val selectedItem: ImageVector
)

