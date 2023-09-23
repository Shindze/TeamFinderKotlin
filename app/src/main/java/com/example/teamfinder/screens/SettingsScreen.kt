package com.example.teamfinder.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.teamfinder.dataStore
import com.jamal.composeprefs3.ui.PrefsScreen
import com.jamal.composeprefs3.ui.prefs.CheckBoxPref
import com.jamal.composeprefs3.ui.prefs.SwitchPref

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(goBackFun: NavHostController) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            navigationIcon = {
                IconButton(onClick = { goBackFun.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            title = {
                Text(
                    text = "Настройки", style = MaterialTheme.typography.titleLarge
                )
            }
        )
    }) { innerPadding ->
        PrefsScreen(dataStore = LocalContext.current.dataStore, Modifier.padding(innerPadding)) {
            prefsGroup("Настройки переключателей") {
                prefsItem {
                    SwitchPref(
                        key = "sw3",
                        title = "Следовать системной теме",
                        summary = "Тема будет меняться в зависимости от системной",
                        enabled = false,
                        leadingIcon = { Icon(Icons.Filled.Info, "Night Theme") }
                    )
                }
            }

            prefsGroup("Уведомления") {
                prefsItem {
                    CheckBoxPref(
                        key = "cb1",
                        title = "Уведомления о сообщениях",
                        summary = "Пуш-уведомления о входящих сообщениях"
                    )
                }
                prefsItem {
                    CheckBoxPref(
                        key = "cb2",
                        title = "Уведомления о отзывах",
                        summary = "Пуш-уведомления о отзыве в группу"
                    )
                }
                prefsItem {
                    CheckBoxPref(
                        key = "cb3",
                        title = "Уведомления о сообщениях",
                        summary = "Пуш-уведомления о входящих сообщениях"
                    )
                }
            }
        }
    }
}