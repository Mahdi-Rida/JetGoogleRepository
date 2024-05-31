package com.monty.jetgooglerepository.android.presentation.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.monty.jetgooglerepository.android.BuildConfig
import com.monty.jetgooglerepository.android.R
import com.monty.jetgooglerepository.android.presentation.navigation.Routes
import com.monty.jetgooglerepository.android.presentation.screens.splash.components.SplashLottieAnimation
import com.monty.jetgooglerepository.android.presentation.screens.splash.viewmodel.SplashState
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme

@Composable
fun SplashScreen(
    state: SplashState,
    navController: NavController
) {

    LaunchedEffect(key1 = state.isLoading) {
        if(!state.isLoading){
            navController.navigate(Routes.MainScreen) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SplashLottieAnimation()
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.version, BuildConfig.VERSION_NAME),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun SplashScreen_Preview() {
    AppTheme {
        SplashScreen(
            state = SplashState(),
            navController = NavController(LocalContext.current)
        )
    }
}
