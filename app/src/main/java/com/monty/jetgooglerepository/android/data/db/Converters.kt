package com.monty.jetgooglerepository.android.data.db

import androidx.room.TypeConverter
import com.monty.jetgooglerepository.android.data.models.Owner

/**
 * This class will convert the avatar url to a column in repositoryTable
 *
 */
class Converters {

    @TypeConverter
    fun fromOwner(owner: Owner) : String{
        return owner.avatarUrl
    }

    @TypeConverter
    fun toOwner(avatarUrl: String) : Owner{
        return Owner(avatarUrl)
    }
}