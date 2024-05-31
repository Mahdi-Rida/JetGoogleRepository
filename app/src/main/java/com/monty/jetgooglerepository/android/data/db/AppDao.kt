package com.monty.jetgooglerepository.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.monty.jetgooglerepository.android.data.models.RepositoryItem

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repositoryItem: RepositoryItem)

    @Query("select * from repositoryTable where page=:page and name like :query")
    fun getRepositories(query:String,page: Int): List<RepositoryItem>
}