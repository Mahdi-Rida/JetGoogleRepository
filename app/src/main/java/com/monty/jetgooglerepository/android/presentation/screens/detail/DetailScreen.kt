package com.monty.jetgooglerepository.android.presentation.screens.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monty.jetgooglerepository.android.presentation.screens.detail.components.DetailScreenLandscape
import com.monty.jetgooglerepository.android.presentation.screens.detail.components.DetailScreenPortrait
import com.monty.jetgooglerepository.android.presentation.screens.detail.viewmodel.DetailState
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme
import com.monty.jetgooglerepository.android.presentation.utils.DataProvider
import com.monty.jetgooglerepository.android.presentation.utils.WindowInfo
import com.monty.jetgooglerepository.android.presentation.utils.navigateBack
import com.monty.jetgooglerepository.android.presentation.utils.rememberWindowInfo

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    state: DetailState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController
) {
    val windowInfo = rememberWindowInfo()

    if (state.repositoryItem != null) {
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            DetailScreenPortrait(
                repositoryItem = state.repositoryItem,
                animatedVisibilityScope = animatedVisibilityScope,
                onCloseClick = {
                    navController.navigateBack()
                }
            )
        } else {
            DetailScreenLandscape(
                repositoryItem = state.repositoryItem,
                animatedVisibilityScope = animatedVisibilityScope,
                onCloseClick = {
                    navController.navigateBack()
                }
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(
    backgroundColor = 0xFFE6E6E6, showBackground = true,
    device = "spec:parent=pixel_5"
)
@Composable
fun DetailScreen_Preview() {
    AppTheme {
        SharedTransitionLayout {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "detail"
            ) {
                composable("detail") {
                    DetailScreen(
                        state = DetailState(
                            repositoryItem = DataProvider.getFakeRepositoryList().first()
                        ),
                        animatedVisibilityScope = this,
                        navController = navController
                    )
                }
            }
        }
    }
}
