package com.monty.jetgooglerepository.android.data.repository.datasource

import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getRepositories(
        repositoryName: String,
        page: Int
    ): Response<List<RepositoryItem>>
}