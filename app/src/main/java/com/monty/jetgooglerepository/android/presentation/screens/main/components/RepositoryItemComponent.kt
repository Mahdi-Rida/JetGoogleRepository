package com.monty.jetgooglerepository.android.presentation.screens.main.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.monty.jetgooglerepository.android.R
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.presentation.navigation.Routes
import com.monty.jetgooglerepository.android.presentation.screens.main.MainScreen
import com.monty.jetgooglerepository.android.presentation.screens.main.viewmodel.MainState
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme
import com.monty.jetgooglerepository.android.presentation.theme.TextColorHint
import com.monty.jetgooglerepository.android.presentation.utils.DataProvider
import com.monty.jetgooglerepository.android.presentation.utils.DateTimeUtil

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.RepositoryItemComponent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    repositoryItem: RepositoryItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surface)
                .clickable { onClick() }
                .testTag(stringResource(R.string.testtag_repositoryitem))
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    model = repositoryItem.owner.avatarUrl,
                    loading = placeholder(R.drawable.repository_placeholder),
                    transition = CrossFade,
                    contentScale = ContentScale.Fit,
                    contentDescription = stringResource(R.string.contentdescription_repositoryimage),
                    modifier = Modifier
                        .size(50.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .sharedElement(
                            state = rememberSharedContentState(key = "${repositoryItem.id}/${repositoryItem.owner.avatarUrl}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 4.dp, horizontal = 12.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
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
                        )

                        Text(
                            text = DateTimeUtil.parseRepositoryDate(repositoryItem.createdAt),
                            style = MaterialTheme.typography.titleSmall,
                            color = TextColorHint,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun RepositoryItemComponent_Preview() {
    AppTheme {
        SharedTransitionLayout {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.MainScreen) {
                composable<Routes.MainScreen> {
                    RepositoryItemComponent(
                        repositoryItem = DataProvider.getFakeRepositoryList().first(),
                        animatedVisibilityScope = this,
                        onClick = {}
                    )
                }
            }
        }
    }
}