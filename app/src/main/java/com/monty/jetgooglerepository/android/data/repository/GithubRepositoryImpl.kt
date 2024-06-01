package com.monty.jetgooglerepository.android.data.repository

import android.util.Log
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.data.repository.datasource.LocalDataSource
import com.monty.jetgooglerepository.android.data.repository.datasource.RemoteDataSource
import com.monty.jetgooglerepository.android.data.utils.Resource
import com.monty.jetgooglerepository.android.domain.repository.GithubRepository
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

class GithubRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : GithubRepository {

    override suspend fun getSearchedRepository(
        repositoryName: String,
        page: Int
    ): Resource<List<RepositoryItem>> {

        //Search in local database
        val repositoryList = localDataSource.getSearchedRepositoryFromDatabase(repositoryName, page)
        if (repositoryList.isNotEmpty()) {
            //found in local database
            return Resource.Success(repositoryList)
        }

        //delay for typing purposes
        delay(1000)

        try {
            val response = remoteDataSource.getRepositories(
                repositoryName = repositoryName,
                page = page
            )
            when (response.code()) {
                // Success response
                in 200..300 -> {
                    response.body()?.let {
                        it.forEach { repositoryItem ->
                            repositoryItem.page = page
                            repositoryItem.repoName = repositoryName
                            localDataSource.insertRepositoryToDatabase(repositoryItem)
                        }
                        return Resource.Success(it)
                    }
                }

                // API returned an error
                400 -> return Resource.Error("The request is invalid, please try again later !")
                401 -> return Resource.Error("Your token is expired! please contact the administrator")
                403 -> return Resource.Error("Your request has been refused by the server, please try again later")
                404 -> return Resource.Success(emptyList())
                406 -> return Resource.Success(emptyList())
                500 -> return Resource.Error("Internal server error, please try again later")
                else -> return Resource.Error(response.message())
            }
            return Resource.Success(emptyList())

        } catch (e: CancellationException) {
            // Coroutine was cancelled
            return Resource.Success(emptyList())
        } catch (e: UnknownHostException) {
            // no internet connection
            return Resource.Error("Please check your internet connection")
        } catch (e: SocketTimeoutException) {
            // timeout
            return Resource.Error("Request time out: ${e.message}")
        } catch (e: IOException) {
            // other IO exceptions
            return Resource.Error("Network error: ${e.message}")
        } catch (e: Exception) {
            // any other exceptions
            return Resource.Error("Error: ${e.message}")
        }
    }
}