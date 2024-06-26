package com.monty.jetgooglerepository.android.data.repository.datasourceimpl

import com.monty.jetgooglerepository.android.data.db.AppDao
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.data.repository.datasource.LocalDataSource

class LocalDataSourceImpl(
    private val appDao: AppDao
):LocalDataSource {

    override fun getSearchedRepositoryFromDatabase(query: String, page: Int): List<RepositoryItem> {
       return appDao.getRepositories(query, page)
    }

    override fun getRepositoryFromDatabase(id: Int): RepositoryItem {
        return appDao.getRepository(id)
    }

    override suspend fun insertRepositoryToDatabase(repositoryItem: RepositoryItem) {
       appDao.insert(repositoryItem)
    }

}