package com.example.themeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.themeapp.data.AppDataStore
import com.example.themeapp.model.CustomTheme
import com.example.themeapp.ui.navigation.data.ScreenDataModel
import com.example.themeapp.ui.navigation.components.BottomNavigationBar
import com.example.themeapp.ui.navigation.components.ScreenA
import com.example.themeapp.ui.navigation.components.ScreenB
import com.example.themeapp.ui.navigation.components.ScreenC
import com.example.themeapp.ui.navigation.data.BottomNavigationModel
import com.example.themeapp.ui.theme.AppTheme

class MainActivity: ComponentActivity() {

    private lateinit var appDataStore: AppDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDataStore = AppDataStore(applicationContext)
        setContent {
            val themeFlow = appDataStore.theme.collectAsState(initial = CustomTheme.A)
            AppTheme(theme = themeFlow.value) {
                MainScreen(dataStore = appDataStore)
            }
        }
    }
}

@Composable
fun MainScreen(dataStore: AppDataStore) {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationBar(navController = navController)
    }) { paddingValues ->
        Box(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            Column(modifier = Modifier.fillMaxSize()) {
                NavigationGraph(navController = navController, dataStore = dataStore)
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController,
                    startDestination: String = "screenB",
                    dataStore: AppDataStore) {
    NavHost(navController, startDestination = startDestination) {
        composable(BottomNavigationModel.A.navigationPath) {
            val screenDataModel = ScreenDataModel(textId = R.string.screen_A)
            ScreenA(screenDataModel)
        }
        composable(BottomNavigationModel.B.navigationPath) {
            val screenDataModel = ScreenDataModel(textId = R.string.screen_B)
            ScreenB(screenDataModel, dataStore)
        }
        composable(BottomNavigationModel.C.navigationPath) {
            val screenDataModel = ScreenDataModel(textId = R.string.screen_C)
            ScreenC(screenDataModel)
        }
    }
}
