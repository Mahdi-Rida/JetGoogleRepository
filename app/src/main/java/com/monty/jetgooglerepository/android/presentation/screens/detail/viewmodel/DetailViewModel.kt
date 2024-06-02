package com.monty.jetgooglerepository.android.presentation.screens.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monty.jetgooglerepository.android.data.models.Owner
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1000L),
        DetailState()
    )

    fun setRepositoryItem(
        id:Int
    ){
        viewModelScope.launch(Dispatchers.IO) {
            val repositoryItem = githubRepository.getRepository(id)
            _state.update {
                it.copy(
                    repositoryItem = repositoryItem
                )
            }
        }
    }
}