package com.monty.jetgooglerepository.android.presentation.navigation

import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object SplashScreen

    @Serializable
    object MainScreen

    @Serializable
    data class DetailScreen(
        val id:Int,
        val repoName:String,
        val avatarUrl:String,
        val starCount: Int
    )
}