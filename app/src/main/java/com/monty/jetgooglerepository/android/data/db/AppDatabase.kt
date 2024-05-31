package com.monty.jetgooglerepository.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.monty.jetgooglerepository.android.data.models.RepositoryItem

@Database(
    entities = [RepositoryItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getAppDao() : AppDao

}