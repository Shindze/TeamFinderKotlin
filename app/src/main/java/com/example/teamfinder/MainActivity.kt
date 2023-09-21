package com.example.teamfinder

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teamfinder.screens.Drawer
import com.example.teamfinder.screens.SettingsScreen
import com.example.teamfinder.ui.theme.TeamFinderTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            TeamFinderTheme {
                val systemUiController = rememberSystemUiController()
                if (isSystemInDarkTheme()) {
                    systemUiController.setSystemBarsColor(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                    )
                } else {
                    systemUiController.setSystemBarsColor(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                    )
                }
                NavHost(
                    navController = navController,
                    startDestination = "start_screen"
                ) {
                    composable("start_screen") {
                        Drawer{
                            navController.navigate("settings_screen")
                        }
                    }
                    composable("settings_screen") {
                        SettingsScreen{
                            navController.navigate("start_screen")
                        }
                    }
                }
            }
        }
    }
}