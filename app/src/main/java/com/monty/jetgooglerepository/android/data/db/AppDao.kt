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

    @Query("SELECT * FROM repositoryTable WHERE page=:page AND repoName LIKE :query")
    fun getRepositories(query:String,page: Int): List<RepositoryItem>

    @Query("SELECT * FROM repositoryTable WHERE id=:id")
    fun getRepository(id:Int) : RepositoryItem
}