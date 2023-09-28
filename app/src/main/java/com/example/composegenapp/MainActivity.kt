package com.example.composegenapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.common.onError
import com.example.composegenapp.common.onSuccess
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
                        mutableIntStateOf(0)
                    }

                    val pagerState = rememberPagerState {
                        tabItems.size
                    }

                    LaunchedEffect(selectedTabIndex) {
                        pagerState.animateScrollToPage(selectedTabIndex)
                        Log.d("1>>>>", "$selectedTabIndex")
                    }
                    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                        if (!pagerState.isScrollInProgress) {
                            selectedTabIndex = pagerState.currentPage
                            Log.d("2>>>>", "$selectedTabIndex")
                        }
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
                            when (index) {
                                HOME_TAB -> ShowHome()
                                ROCKETS_TAB -> ShowItems()
                                ACCOUNT_TAB -> LoginScreen()
                            }
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


                    }
                }
            }

        }
    }

    companion object {
        const val HOME_TAB = 0
        const val ROCKETS_TAB = 1
        const val ACCOUNT_TAB = 2
    }

}


@Composable
private fun ShowHome() {
    Animations.AnimBox(resId = R.drawable.collection_olive_hoodie_photo_main_page_2000x)
}


@Composable
private fun ShowItems() {

    val resLoad =
        produceState<ResponseResult<List<FalconInfo>>>(initialValue = ResponseResult.Loading) {
            value = try {
                ResponseResult.Success(SpaceX().getRockets())
            } catch (e: Exception) {
                ResponseResult.Error(e.localizedMessage ?: "error")
            }
        }

    resLoad.let {
        when (it.value) {
            is ResponseResult.Loading -> ShowProgressOrError("Loading...")
            is ResponseResult.Success -> {
                it.value.onSuccess { rockets ->
                    FalconInfoListView(rockets)
                }
            }

            is ResponseResult.Error -> it.value.onError { error ->
                ShowProgressOrError(status = error)
            }

        }
    }
}

@Composable
fun ShowProgressOrError(status: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = status)
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
