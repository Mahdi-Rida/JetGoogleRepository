package com.monty.jetgooglerepository.android.presentation.screens.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monty.jetgooglerepository.android.data.models.Owner
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1000L),
        DetailState()
    )

    fun setRepositoryItem(
        id:Int,
        name:String,
        starCount:Int,
        avatarUrl:String
    ){
        _state.update {
            it.copy(
                repositoryItem = RepositoryItem(
                    id = id,
                    name = name,
                    owner = Owner(avatarUrl),
                    stargazersCount = starCount,
                    createdAt = "",
                )
            )
        }
    }
}