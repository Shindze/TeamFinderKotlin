package com.example.teamfinder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.example.teamfinder.datastore.Profile
import com.example.teamfinder.datastore.profiles

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun TinderLikeScreen() {
    val states = profiles.reversed().map { it to rememberSwipeableCardState() }
    Box(
        Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        states.forEach { (profile, state) ->
            if (state.swipedDirection == null) {
                ProfileCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .swipableCard(
                            state = state,
                            blockedDirections = listOf(Direction.Down),
                            onSwiped = {},
                            onSwipeCancel = {}
                        ),
                    Profile = profile
                )
            }
            LaunchedEffect(profile, state.swipedDirection) {}
        }
    }
}


@Composable
private fun ProfileCard(
    modifier: Modifier,
    Profile: Profile,
) {
    Card(modifier) {
        Box {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(Profile.drawableResId),
                contentDescription = null
            )
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = Profile.name,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}