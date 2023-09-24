package com.example.teamfinder.datastore

import androidx.annotation.DrawableRes

data class Profile(
    val name: String,
    @DrawableRes val drawableResId: Int,
)

val profiles = listOf(
    Profile("Поляков Даниил", androidx.core.R.drawable.ic_call_answer),
    Profile("Баженов Андрей", androidx.core.R.drawable.ic_call_answer),
    Profile("Крупнов Дорофил", androidx.core.R.drawable.ic_call_answer),
    Profile("Илюха", androidx.core.R.drawable.ic_call_answer),
)