package com.monty.jetgooglerepository.android.data.repository.datasourceimpl

import com.monty.jetgooglerepository.android.data.api.ApiService
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.data.repository.datasource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun getRepositories(
        repositoryName: String,
        page: Int
    ): Response<List<RepositoryItem>> {
        return apiService.getSearchedRepositories(
            query = repositoryName,
            page = page,
            perPage = 10
        )
    }
}