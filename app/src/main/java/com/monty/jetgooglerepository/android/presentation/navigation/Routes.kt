package com.monty.jetgooglerepository.android.presentation.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object SplashScreen

    @Serializable
    object MainScreen

    @Serializable
    data class DetailScreen(
        val id:Int
    )
}