package com.monty.jetgooglerepository.android.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.monty.jetgooglerepository.android.presentation.screens.detail.DetailScreen
import com.monty.jetgooglerepository.android.presentation.screens.detail.viewmodel.DetailViewModel
import com.monty.jetgooglerepository.android.presentation.screens.main.MainScreen
import com.monty.jetgooglerepository.android.presentation.screens.main.viewmodel.MainViewModel
import com.monty.jetgooglerepository.android.presentation.screens.splash.SplashScreen
import com.monty.jetgooglerepository.android.presentation.screens.splash.viewmodel.SplashViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation() {

    SharedTransitionLayout {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Routes.SplashScreen) {

            composable<Routes.SplashScreen> {
                val viewModel = hiltViewModel<SplashViewModel>()
                val state by viewModel.state.collectAsState()

                SplashScreen(state = state, navController = navController)
            }

            composable<Routes.MainScreen>(
                enterTransition = {
                    fadeIn(tween(700)) + slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(700)
                    )
                },
                exitTransition = { fadeOut(tween(400)) },
                popEnterTransition = { fadeIn(tween(400)) },
                popExitTransition = { fadeOut(tween(400)) }
            ) {
                val viewModel = hiltViewModel<MainViewModel>()
                val state by viewModel.state.collectAsState()

                MainScreen(
                    state = state,
                    onEvent = { viewModel.onEvent(it) },
                    navController = navController,
                    animatedVisibilityScope = this
                )
            }

            composable<Routes.DetailScreen>
            { backStack ->
                val args = backStack.toRoute<Routes.DetailScreen>()
                val repoName = args.repoName
                val avatarUrl = args.avatarUrl
                val id = args.id
                val starCount = args.starCount

                val viewModel = hiltViewModel<DetailViewModel>()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.setRepositoryItem(
                        id = id,
                        name = repoName,
                        avatarUrl = avatarUrl,
                        starCount = starCount
                    )
                }

                DetailScreen(
                    state = state,
                    animatedVisibilityScope = this,
                    navController = navController
                )
            }
        }
    }
}