package com.example.composegenapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composegenapp.common.Constants.Companion.HOME_SCREEN
import com.example.composegenapp.common.Constants.Companion.LOGIN_SCREEN
import com.example.composegenapp.common.Constants.Companion.ROCKETS_SCREEN
import com.example.composegenapp.data.FalconInfo
import com.example.composegenapp.data.SampleData
import com.example.composegenapp.ui.BottomTabPanel
import com.example.composegenapp.ui.FalconInfoCard
import com.example.composegenapp.ui.FalconInfoItemsScreen
import com.example.composegenapp.ui.HomeScreen
import com.example.composegenapp.ui.LoginScreen
import com.example.composegenapp.ui.theme.ComposeGenAppTheme

class MainActivity : ComponentActivity() {
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
                        composable (HOME_SCREEN) { HomeScreen() }
                        composable (ROCKETS_SCREEN) { FalconInfoItemsScreen() }
                        composable (LOGIN_SCREEN) { LoginScreen() }
                    }

                    BottomTabPanel(navController)

                }
            }

        }
    }

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
