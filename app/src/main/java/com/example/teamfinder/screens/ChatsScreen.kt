package com.example.teamfinder.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teamfinder.CHAT_SCREEN

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatsScreen(navController: NavHostController) {

    Scaffold {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            repeat(3) {
                chatButton(navController)
            }
        }
    }
}

@Composable
fun chatButton(navController: NavHostController) {
    Box(Modifier.size(12.dp)) {}
    Box(
        Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .clickable {
                navController.navigate(CHAT_SCREEN)
            }
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(72.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.outline)
            )
            Box(Modifier.width(12.dp))
            Column(Modifier.fillMaxSize()) {
                Text(
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    text = "Разработчик",
                    style = MaterialTheme.typography.titleMedium
                )
                Box(Modifier.height(4.dp))
                Text(
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    text = "Завтра сможем встретиться о вакансии?",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}