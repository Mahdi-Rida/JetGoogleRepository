package com.monty.jetgooglerepository.android.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "repositoryTable")
data class RepositoryItem(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @SerializedName("created_at")
    val createdAt: String,

    var page: Int = 0
)