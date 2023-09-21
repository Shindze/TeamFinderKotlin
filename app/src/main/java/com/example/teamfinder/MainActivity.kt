package com.example.teamfinder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.ui.unit.dp
import com.example.teamfinder.screens.Drawer
import com.example.teamfinder.ui.theme.TeamFinderTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                Drawer()
            }
        }
    }
}