package com.example.teamfinder.screens

import BottomSheet
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.teamfinder.datastore.WorkDataClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val works = listOf(
    WorkDataClass(
        workName = "Разработчик",
        workDescription = "Требуется создатель моделей в 3д",
        workTag = "Game"
    ),
    WorkDataClass(
        workName = "Создатель",
        workDescription = "Мы ищем того, кто будет творить!",
        workTag = "Other"
    ),
    WorkDataClass(
        workName = "Игрок",
        workDescription = "Хочешь поиграть? Тебе к нам!",
        workTag = "Game"
    ),
    WorkDataClass(
        workName = "Водитель",
        workDescription = "Ищем водителей категории B",
        workTag = "Other"
    ),
    WorkDataClass(
        workName = "Разработчик",
        workDescription = "Требуется создатель моделей в 3д",
        workTag = "Game"
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(scope: CoroutineScope, drawerState: DrawerState) {

    val sheetState = rememberModalBottomSheetState()

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val items = listOf(
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

    if (sheetState.isVisible) {
        BottomSheet(sheetState, scope)
    }

    Scaffold(Modifier.fillMaxSize(),
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
                    Text(text = "Библиотека", color = MaterialTheme.colorScheme.onSurface)
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
                items.forEachIndexed { index, item ->
                    NavigationBarItem(icon = {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            }, contentDescription = item.title
                        )
                    }, label = {
                        Text(item.title)
                    }, selected = selectedItemIndex == index, onClick = {
                        selectedItemIndex = index
                    })
                }
            }
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            libraryScreenElements(sheetState, scope)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun libraryScreenElements(sheetState: SheetState, scope: CoroutineScope) {
    Column(
        Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .fillMaxSize(),
    ) {

        workCard(scope, sheetState, works[1])
        Text(
            text = "Ваш выбор",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            for (work in works){
                workCard(scope, sheetState, work)
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
        ) {
            Text(
                text = "Может понравиться",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(modifier = Modifier.size(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "Пока ничего не можем подобрать :(",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Right,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun workCard(scope: CoroutineScope, sheetState: SheetState, work: WorkDataClass) {
    Column(
        Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Box(
            Modifier
                .size(164.dp)
                .clip(shape = RoundedCornerShape(28.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .clickable {
                    scope.launch {
                        sheetState.show()
                    }
                }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Box(
                        Modifier
                            .size(48.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.secondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Build,
                            contentDescription = "Game",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Text(
                        text = "3.4 км",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Box(Modifier.size(8.dp)) {}
                Column(
                    Modifier.fillMaxSize(),
                ) {
                    Text(
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        text = work.workName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Box(Modifier.size(4.dp)) {}
                    Text(
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        text = work.workDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}