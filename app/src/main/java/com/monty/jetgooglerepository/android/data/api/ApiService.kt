package com.monty.jetgooglerepository.android.data.api

import com.monty.jetgooglerepository.android.BuildConfig
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /**
     * This request will search for repositories
     *
     * @param query : query text
     * @param page : page number
     */
    @GET("{repo}/repos")
    suspend fun getSearchedRepositories(
        @Header("Authorization") token: String = BuildConfig.API_KEY,
        @Path("repo") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String = "created_at"
    ) : Response<List<RepositoryItem>>
}