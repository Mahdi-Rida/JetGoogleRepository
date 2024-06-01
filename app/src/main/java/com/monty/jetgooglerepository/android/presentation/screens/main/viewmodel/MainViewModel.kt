package com.monty.jetgooglerepository.android.presentation.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.data.utils.Resource
import com.monty.jetgooglerepository.android.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), MainState())
    private var job: Job? = null
    private var pageIncrement: Int = 1
    private var startPage = 1
    private var queryText = ""

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetSearchedRepositories -> getSearchedRepositories(event.query)
            MainEvent.OnErrorSeen -> setError("")
            MainEvent.ClearRepositoryList -> clearRepositoryList()
            MainEvent.LoadNextRepositories -> loadNextItems()
        }
    }

    private fun clearRepositoryList() {
        _state.update {
            it.copy(
                repositoryList = emptyList(),
                queryText = "",
                showSearchImage = true,
                page = startPage
            )
        }
    }

    private fun setError(error: String) {
        _state.update {
            it.copy(
                error = error,
                isLoading = false
            )
        }
    }

    private fun getSearchedRepositories(query: String) {
        //canceling the current job
        job?.cancel()

        val oldQueryText = state.value.queryText
        _state.update { it.copy(queryText = query) }

        if (state.value.queryText.isEmpty()) {

            _state.update {
                it.copy(
                    isLoading = false,
                    showNotFound = false,
                    showSearchImage = state.value.repositoryList.isEmpty(),
                    page = startPage
                )
            }
            return
        }

        if (!oldQueryText.equals(query, true)) {
            _state.update {
                it.copy(
                    repositoryList = emptyList(),
                    showSearchImage = true,
                    page = startPage
                )
            }
        }
        queryText = query
        loadNextItems()

    }

    private fun loadNextItems() {

        job = viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }

            val result = repository.getSearchedRepository(
                repositoryName = queryText,
                page = state.value.page
            )
            when (result) {
                is Resource.Error -> {
                    setError(result.message.toString())
                }

                is Resource.Success -> {
                    _state.update { currentState ->
                        val updatedRepositoryList =
                            currentState.repositoryList + (result.data ?: emptyList())
                        currentState.copy(
                            repositoryList = state.value.repositoryList + result.data!!,
                            page = state.value.page + pageIncrement,
                            endReached = result.data.isEmpty(),
                            showSearchImage = false,
                            isLoading = false,
                            showNotFound = updatedRepositoryList.isEmpty()
                        )
                    }
                }
            }
        }
    }
}