package com.monty.jetgooglerepository.android.presentation.screens.detail.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.presentation.appcomponents.CloseButton
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme
import com.monty.jetgooglerepository.android.presentation.utils.DataProvider

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreenPortrait(
    repositoryItem: RepositoryItem,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onCloseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        CloseButton(
            icon = Icons.Default.Close,
            modifier = Modifier.align(Alignment.End),
            onClick = { onCloseClick() }
        )

        DetailRepositoryImage(
            avatarUrl = repositoryItem.owner.avatarUrl,
            sharedElementKey = "${repositoryItem.id}/${repositoryItem.owner.avatarUrl}",
            animatedVisibilityScope = animatedVisibilityScope,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = repositoryItem.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "${repositoryItem.id}/${repositoryItem.name}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
                    .weight(1f)
                    .padding(end = 16.dp)

            )
            StarGazer(count = repositoryItem.stargazersCount)
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun DetailScreenPortrait_Preview() {
    AppTheme {
        SharedTransitionLayout {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "detailPortrait"
            ) {
                composable("detailPortrait") {
                    DetailScreenPortrait(
                        repositoryItem = DataProvider.getFakeRepositoryList().first(),
                        animatedVisibilityScope = this,
                        onCloseClick = {}
                    )
                }
            }
        }
    }
}