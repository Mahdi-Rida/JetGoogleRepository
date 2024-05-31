package com.monty.jetgooglerepository.android.domain.repository

import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.data.utils.Resource

interface GithubRepository {

    /**
     * This function will search in local database first...
     * if nothing found then will fetch from the api service
     *
     * @param repositoryName : the repository name that you search for
     * @param page : page number
     */
    suspend fun getSearchedRepository(
        repositoryName: String,
        page: Int
    ): Resource<List<RepositoryItem>>

}