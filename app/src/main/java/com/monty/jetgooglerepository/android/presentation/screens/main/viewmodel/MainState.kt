package com.monty.jetgooglerepository.android.presentation.screens.main.viewmodel

import com.monty.jetgooglerepository.android.data.models.RepositoryItem

data class MainState(
    val isLoading: Boolean = false,
    val error: String = "",
    val queryText: String = "",
    val repositoryList: List<RepositoryItem> = emptyList(),
    val showNotFound: Boolean = false,
    val showSearchImage: Boolean = true,
    val endReached: Boolean = false,
    val page: Int = 1,
    val toggle:Boolean = false,
)
