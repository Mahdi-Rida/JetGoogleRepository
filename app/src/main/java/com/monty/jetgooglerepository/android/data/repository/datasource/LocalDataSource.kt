package com.monty.jetgooglerepository.android.data.repository.datasource

import com.monty.jetgooglerepository.android.data.models.RepositoryItem

interface LocalDataSource {

   suspend fun getSearchedRepositoryFromDatabase(
        query: String,
        page: Int
    ): List<RepositoryItem>

    suspend fun insertRepositoryToDatabase(repositoryItem: RepositoryItem)
}