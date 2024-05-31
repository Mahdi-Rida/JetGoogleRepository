package com.monty.jetgooglerepository.android.data.repository.datasource

import com.monty.jetgooglerepository.android.data.models.RepositoryItem

interface LocalDataSource {

    /**
     * This function will return the repository item list from the local database..
     * I'am not using Flow because it's not a live update to the UI
     *
     * @param query : query text
     * @param page : page number
     */
   suspend fun getSearchedRepositoryFromDatabase(
        query: String,
        page: Int
    ): List<RepositoryItem>

    suspend fun insertRepositoryToDatabase(repositoryItem: RepositoryItem)
}