package com.monty.jetgooglerepository.android.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val GrayDark = Color(0xFF282C31)
val Gray = Color(0xFF32373D)
val GrayLight = Color(0xFFF6F4F4)
val Blue = Color(0xFF3779E4)
val TextBlack = Color(0xFF111111)
val TextColorHint = Color(0xFFA5A5A5)

val lightColors = lightColorScheme(
    primary = Blue,
    background = GrayLight,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack
)

val darkColors = darkColorScheme(
    primary = Blue,
    background = GrayDark,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = Gray,
    onSurface = Color.White
)