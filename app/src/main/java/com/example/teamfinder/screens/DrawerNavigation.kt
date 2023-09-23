package com.example.teamfinder.screens

import BottomSheet
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

data class DrawerItem(
    val imageVector: ImageVector,
    val title: String
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerShell(navigation: NavHostController) {

    val items = listOf(
        DrawerItem(
            imageVector = Icons.Filled.Home,
            title = "Библиотека"
        ),
        DrawerItem(
            imageVector = Icons.Filled.Notifications,
            title = "Уведомления"
        ),
        DrawerItem(
            imageVector = Icons.Filled.Settings,
            title = "Настройки"
        ),
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val selectedItem = remember {
        mutableStateOf(items[0])
    }

    var selectedBottomItem by rememberSaveable {
        mutableStateOf(0)
    }
    val bottomItems = listOf(
        BottomNavItem(
            title = "Library",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ), BottomNavItem(
            title = "Tinder", selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face
        ), BottomNavItem(
            title = "Favourites",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Rounded.FavoriteBorder
        )
    )

    val sheetState = rememberModalBottomSheetState()

    if (sheetState.isVisible) {
        BottomSheet(sheetState, scope)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Team Finder",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                )
                items.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = selectedItem.value == item,
                        onClick = {
                            scope.launch {
                                selectedItem.value = item
                                if (selectedItem.value.title == "Настройки") navigation.navigate(
                                    "settings_screen"
                                )
                            }
                        },
                        Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        icon = {
                            Icon(
                                imageVector = item.imageVector,
                                contentDescription = item.title
                            )
                        },
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
        content = {
            Scaffold(
                Modifier
                    .fillMaxSize(),
                containerColor = MaterialTheme.colorScheme.background,
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        ),
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        },
                        title = {
                            Text(
                                text = "Библиотека",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "Localized description",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        },
                    )
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    ) {
                        bottomItems.forEachIndexed { index, item ->
                            NavigationBarItem(icon = {
                                Icon(
                                    imageVector = if (index == selectedBottomItem) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    }, contentDescription = item.title
                                )
                            }, label = {
                                Text(item.title)
                            }, selected = selectedBottomItem == index, onClick = {
                                selectedBottomItem = index
                            })
                        }
                    }
                }
            ) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    LibraryScreen(scope, drawerState, sheetState)
                }
            }
        },
    )
}

