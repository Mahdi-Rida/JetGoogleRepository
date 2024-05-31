package com.monty.jetgooglerepository.android.data.repository.datasource

import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import retrofit2.Response

interface RemoteDataSource {

    /**
     * This function will make a network request to Github api to fetch data
     *
     * @param repositoryName : repository name
     * @param page : page number
     */
    suspend fun getRepositories(
        repositoryName: String,
        page: Int
    ): Response<List<RepositoryItem>>
}