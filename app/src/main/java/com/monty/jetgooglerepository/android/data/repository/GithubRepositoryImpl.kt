package com.monty.jetgooglerepository.android.data.repository

import android.util.Log
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.data.repository.datasource.LocalDataSource
import com.monty.jetgooglerepository.android.data.repository.datasource.RemoteDataSource
import com.monty.jetgooglerepository.android.data.utils.Resource
import com.monty.jetgooglerepository.android.domain.repository.GithubRepository
import retrofit2.HttpException
import java.net.SocketTimeoutException

class GithubRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : GithubRepository {
    private val TAG = "GithubRepositoryImpl"

    override suspend fun getSearchedRepository(
        repositoryName: String,
        page: Int
    ): Resource<List<RepositoryItem>> {


        val repositoryList = localDataSource.getSearchedRepositoryFromDatabase(repositoryName, page)
        if (repositoryList.isNotEmpty()) {
            println("$TAG, get from database")
            return Resource.Success(repositoryList)
        }

        try {
            println("$TAG, get from api")
            val response = remoteDataSource.getRepositories(
                repositoryName = repositoryName,
                page = page
            )
            when (response.code()) {
                in 200..300 -> {
                    response.body()?.let {
                        it.forEach { repositoryItem ->
                            repositoryItem.page = page
                            localDataSource.insertRepositoryToDatabase(repositoryItem)
                        }
                        return Resource.Success(it)
                    }
                }

                400 -> return Resource.Error("The request is invalid, please try again later !")
                401 -> return Resource.Error("Your token is expired! please contact the administrator")
                403 -> return Resource.Error("Your request has been refused by the server, please try again later")
                404 -> return Resource.Success(emptyList())
                406 -> return Resource.Success(emptyList())
                500 -> return Resource.Error("Internal server error, please try again later")
                else -> return Resource.Error(response.message())
            }
            return Resource.Success(emptyList())
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(TAG, "getSearchedRepository: error ${e.localizedMessage}")
            return Resource.Success(emptyList())
        }

    }
}