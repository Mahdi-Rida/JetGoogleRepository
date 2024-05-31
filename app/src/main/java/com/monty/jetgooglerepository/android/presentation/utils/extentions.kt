package com.monty.jetgooglerepository.android.presentation.utils

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController

val NavController.canGoBack: Boolean
    get() = currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

/**
 * This function will ensure that the back press is never performed twice if the user click twice on the close button
 */
fun NavController.navigateBack() {
    if (canGoBack) {
        popBackStack()
    }
}