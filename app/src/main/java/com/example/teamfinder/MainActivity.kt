package com.example.teamfinder

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teamfinder.screens.SettingsScreen
import com.example.teamfinder.screens.chatScreen
import com.example.teamfinder.ui.theme.TeamFinderTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamFinderTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = MaterialTheme.colorScheme.surfaceContainer
                )

                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = "start_screen"
                ) {
                    composable(START_SCREEN) {
                        DrawerShell(navController)
                    }
                    composable(SETTINGS_SCREEN) {
                        SettingsScreen(navController)
                    }
                    composable(CHAT_SCREEN) {
                        chatScreen(navController)
                    }
                }
            }
        }
    }
}

const val START_SCREEN = "start_screen"
const val SETTINGS_SCREEN = "settings_screen"
const val CHAT_SCREEN = "chat_screen"