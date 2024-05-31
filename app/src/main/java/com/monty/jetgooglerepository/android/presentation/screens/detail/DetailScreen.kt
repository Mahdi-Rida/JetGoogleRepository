package com.monty.jetgooglerepository.android.presentation.screens.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.monty.jetgooglerepository.android.R
import com.monty.jetgooglerepository.android.presentation.navigation.Routes
import com.monty.jetgooglerepository.android.presentation.screens.detail.components.DetailRepositoryImage
import com.monty.jetgooglerepository.android.presentation.screens.detail.components.StarGazer
import com.monty.jetgooglerepository.android.presentation.screens.detail.viewmodel.DetailState
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme
import com.monty.jetgooglerepository.android.presentation.utils.DataProvider
import com.monty.jetgooglerepository.android.presentation.utils.WindowInfo
import com.monty.jetgooglerepository.android.presentation.utils.rememberWindowInfo

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    state: DetailState,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val windowInfo = rememberWindowInfo()

    if(state.repositoryItem!=null){
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {

                DetailRepositoryImage(
                    avatarUrl = state.repositoryItem.owner.avatarUrl,
                    sharedElementKey = "${state.repositoryItem.id}/${state.repositoryItem.owner.avatarUrl}",
                    animatedVisibilityScope = animatedVisibilityScope,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = state.repositoryItem.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "${state.repositoryItem.id}/${state.repositoryItem.name}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                            )
                            .weight(1f)
                            .padding(end = 16.dp)

                    )

                    StarGazer(count = state.repositoryItem.stargazersCount)
                }
            }
        }
        else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {

                DetailRepositoryImage(
                    avatarUrl = state.repositoryItem.owner.avatarUrl,
                    sharedElementKey = "${state.repositoryItem.id}/${state.repositoryItem.owner.avatarUrl}",
                    animatedVisibilityScope = animatedVisibilityScope,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .aspectRatio(1f)
                )

                Column(
                    modifier = Modifier
                        .weight(3f)
                        .padding(16.dp),
                ) {
                    Text(
                        text = state.repositoryItem.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "${state.repositoryItem.id}/${state.repositoryItem.name}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                            )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    StarGazer(count = state.repositoryItem.stargazersCount)
                }
            }
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
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}
