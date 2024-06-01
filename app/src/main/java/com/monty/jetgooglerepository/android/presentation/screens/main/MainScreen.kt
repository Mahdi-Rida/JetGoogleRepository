package com.monty.jetgooglerepository.android.presentation.screens.main

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monty.jetgooglerepository.android.R
import com.monty.jetgooglerepository.android.presentation.appcomponents.AppDialog
import com.monty.jetgooglerepository.android.presentation.navigation.Routes
import com.monty.jetgooglerepository.android.presentation.screens.main.components.JumpToTop
import com.monty.jetgooglerepository.android.presentation.screens.main.components.MainScreenClearButton
import com.monty.jetgooglerepository.android.presentation.screens.main.components.MainScreenRepoNotFound
import com.monty.jetgooglerepository.android.presentation.screens.main.components.MainScreenSearchBar
import com.monty.jetgooglerepository.android.presentation.screens.main.components.MainScreenSearchItem
import com.monty.jetgooglerepository.android.presentation.screens.main.components.RepositoryItemComponent
import com.monty.jetgooglerepository.android.presentation.screens.main.viewmodel.MainEvent
import com.monty.jetgooglerepository.android.presentation.screens.main.viewmodel.MainState
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme
import com.monty.jetgooglerepository.android.presentation.utils.DataProvider
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MainScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val jumpThreshold = with(LocalDensity.current) { 56.dp.toPx() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val jumpToTopButtonEnabled by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0 || listState.firstVisibleItemScrollOffset > jumpThreshold
        }
    }
    //this will hide the keyboard when the user start scrolling
    LaunchedEffect(listState.isScrollInProgress, state.repositoryList.isEmpty()) {
        snapshotFlow { listState.isScrollInProgress }
            .distinctUntilChanged()
            .collectLatest { isScrollInProgress ->
                if (isScrollInProgress && state.repositoryList.isNotEmpty()) {
                    keyboardController?.hide()
                }
            }
    }

    if (state.error.isNotEmpty()) {
        AppDialog(
            title = stringResource(R.string.note),
            text = state.error,
            positiveButton = stringResource(R.string.ok),
            negativeButton = "",
            onPositiveClick = {
                onEvent(MainEvent.OnErrorSeen)
            },
            onNegativeClick = {})
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {

            MainScreenSearchBar(
                text = state.queryText,
                hint = stringResource(R.string.enter_repository_name),
                onTextChange = { onEvent(MainEvent.GetSearchedRepositories(it)) })


            MainScreenRepoNotFound(visible = !state.isLoading && state.showNotFound)

            MainScreenSearchItem(visible = !state.isLoading && state.showSearchImage)

            LazyColumn(
                state = listState
            ) {

                item {
                    MainScreenClearButton(
                        visible = state.repositoryList.isNotEmpty(),
                        onclick = { onEvent(MainEvent.ClearRepositoryList) }
                    )
                }

                items(state.repositoryList.size) { i ->
                    val repositoryItem = state.repositoryList[i]
                    if (i >= state.repositoryList.size - 1 && !state.endReached && !state.isLoading) {
                        onEvent(MainEvent.LoadNextRepositories)
                    }
                    RepositoryItemComponent(
                        animatedVisibilityScope = animatedVisibilityScope,
                        repositoryItem = repositoryItem,
                        onClick = {
                            navController.navigate(
                                Routes.DetailScreen(
                                    repoName = repositoryItem.name,
                                    avatarUrl = repositoryItem.owner.avatarUrl,
                                    starCount = repositoryItem.stargazersCount,
                                    id = repositoryItem.id
                                )
                            )
                        }
                    )
                }

                item {
                    if (state.isLoading) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 3.dp,
                            )
                        }
                    }
                }
            }
        }

        JumpToTop(
            enabled = jumpToTopButtonEnabled && state.repositoryList.isNotEmpty(),
            onClicked = {
                scope.launch {
                    listState.scrollToItem(0)
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(
    backgroundColor = 0xFFE6E6E6, showBackground = true,
    device = "spec:parent=pixel_5"
)
@Composable
fun MainScreen_Preview() {
    AppTheme {
        SharedTransitionLayout {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.MainScreen) {
                composable<Routes.MainScreen> {
                    MainScreen(
                        state = MainState(
                            isLoading = true,
                            showNotFound = false,
                            showSearchImage = false,
                            repositoryList = DataProvider.getFakeRepositoryList()
                        ),
                        onEvent = {},
                        navController = NavController(LocalContext.current),
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}
