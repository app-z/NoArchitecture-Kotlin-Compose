package com.example.composegenapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composegenapp.common.Constants.Companion.HOME_SCREEN
import com.example.composegenapp.common.Constants.Companion.LOGIN_SCREEN
import com.example.composegenapp.common.Constants.Companion.ROCKETS_SCREEN
import com.example.composegenapp.data.DataMapper
import com.example.composegenapp.domain.model.FalconInfo
import com.example.composegenapp.domain.usecase.GetFalconInfoUseCase
import com.example.composegenapp.ui.BottomTabPanel
import com.example.composegenapp.ui.FalconInfoCard
import com.example.composegenapp.ui.FalconInfoItemsScreen
import com.example.composegenapp.ui.HomeScreen
import com.example.composegenapp.ui.LoginScreen
import com.example.composegenapp.ui.theme.ComposeGenAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var getFalconInfoUseCase: GetFalconInfoUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {


            ComposeGenAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
                        composable(HOME_SCREEN) { HomeScreen() }
                        composable(ROCKETS_SCREEN) { FalconInfoItemsScreen(getFalconInfoUseCase) }
                        composable(LOGIN_SCREEN) { LoginScreen() }
                    }

                    BottomTabPanel(navController)

                }
            }

        }
    }

}


//-----------------------------------------------------------------------------------------------

@Composable
fun FalconInfoListView(falconInfoEntries: List<FalconInfo>) {
    LazyVerticalGrid(modifier = Modifier
        .background(Color.Black)
        .padding(4.dp),
        columns = GridCells.Fixed(2), content = {
            items(falconInfoEntries) { falconInfo ->
                FalconInfoCard(falconInfo)
            }
        })
}


sealed class Screen(
    val route: String,
    @StringRes val textResId: Int,
    val icon: ImageVector,
    val popUp: Boolean = false
) {
    data object Home : Screen(HOME_SCREEN, R.string.home, Icons.Filled.Home)
    data object Rockets : Screen(ROCKETS_SCREEN, R.string.rockets, Icons.Filled.Home)
    data object Login : Screen(LOGIN_SCREEN, R.string.login, Icons.Filled.Home)

    fun navigate(navController: NavController, route: String) {
        navController.navigate(route) {
            popUpTo(0)
        }
    }

    companion object {
        const val HOME_SCREEN = "homeScreen"
        const val ROCKETS_SCREEN = "rocketsScreen"
        const val LOGIN_SCREEN = "loginScreen"
    }
}


@Preview
@Composable
fun PreviewConversation() {
    ComposeGenAppTheme {
        FalconInfoListView(DataMapper.getRockets())
    }
}
