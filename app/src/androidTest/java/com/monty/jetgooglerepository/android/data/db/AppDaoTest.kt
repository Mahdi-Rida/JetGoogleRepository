package com.monty.jetgooglerepository.android.data.db

import android.content.Context
import androidx.arch.core.executor.TaskExecutor
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.monty.jetgooglerepository.android.data.models.RepositoryItem
import com.monty.jetgooglerepository.android.presentation.utils.DataProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDaoTest {

    private lateinit var appDao: AppDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        appDao = db.getAppDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertRepositoryItem_getRepositoryItemList_ExpectedResult() {
        runBlocking {
            val repositoryItem: RepositoryItem = DataProvider.getFakeRepositoryList().first()
            appDao.insert(repositoryItem)

            val repositoryList =
                appDao.getRepositories(repositoryItem.repoName, repositoryItem.page)
            assertThat(repositoryList.contains(repositoryItem))
        }
    }

    @Test
    fun insertRepositoryItem_getRepositoryItem_ExpectedResult() {
        runBlocking {
            val repositoryItem: RepositoryItem = DataProvider.getFakeRepositoryList().first()
            appDao.insert(repositoryItem)

            val repositoryFromDB = appDao.getRepository(repositoryItem.id)
            assertThat(repositoryFromDB).isEqualTo(repositoryItem)
        }
    }
}