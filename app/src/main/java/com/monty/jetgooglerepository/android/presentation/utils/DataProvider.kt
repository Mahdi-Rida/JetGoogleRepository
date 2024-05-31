package com.monty.jetgooglerepository.android.presentation.utils

import com.monty.jetgooglerepository.android.data.models.Owner
import com.monty.jetgooglerepository.android.data.models.RepositoryItem

object DataProvider {

    fun getFakeRepositoryList(): List<RepositoryItem> {
        val list = ArrayList<RepositoryItem>()
        (1..10).forEach { i ->
            list.add(
                RepositoryItem(
                    id = i,
                    name = "Repository $i",
                    owner = Owner(""),
                    stargazersCount = i,
                    createdAt = "2011-06-22T18:55:12Z",
                    page = 0
                )
            )
        }
        return list
    }
}