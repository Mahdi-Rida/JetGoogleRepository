package com.monty.jetgooglerepository.android.domain.repository

import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.data.utils.Resource

interface GithubRepository {

    suspend fun getSearchedRepository(
        repositoryName: String,
        page: Int
    ): Resource<List<RepositoryItem>>

}