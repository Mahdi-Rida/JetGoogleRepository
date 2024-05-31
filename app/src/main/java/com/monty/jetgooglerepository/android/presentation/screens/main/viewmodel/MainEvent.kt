package com.monty.jetgooglerepository.android.presentation.screens.main.viewmodel

sealed class MainEvent {
    data class GetSearchedRepositories(val query:String) : MainEvent()
    data object OnErrorSeen : MainEvent()
    data object ClearRepositoryList : MainEvent()
    data object LoadNextRepositories : MainEvent()
}