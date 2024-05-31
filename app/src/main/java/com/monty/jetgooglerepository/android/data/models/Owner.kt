package com.monty.jetgooglerepository.android.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(

    @SerializedName("avatar_url")
    val avatarUrl: String

)