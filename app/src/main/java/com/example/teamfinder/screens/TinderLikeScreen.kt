package com.example.teamfinder.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TinderLikeScreen() {
    Column {
        repeat(100) {
            Text(text = "$it")
        }
    }
}