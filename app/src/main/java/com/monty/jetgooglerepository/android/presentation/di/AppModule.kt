package com.monty.jetgooglerepository.android.presentation.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.monty.jetgooglerepository.android.data.api.ApiService
import com.monty.jetgooglerepository.android.data.db.AppDao
import com.monty.jetgooglerepository.android.data.db.AppDatabase
import com.monty.jetgooglerepository.android.data.repository.GithubRepositoryImpl
import com.monty.jetgooglerepository.android.data.repository.datasource.LocalDataSource
import com.monty.jetgooglerepository.android.data.repository.datasource.RemoteDataSource
import com.monty.jetgooglerepository.android.data.repository.datasourceimpl.LocalDataSourceImpl
import com.monty.jetgooglerepository.android.data.repository.datasourceimpl.RemoteDataSourceImpl
import com.monty.jetgooglerepository.android.domain.repository.GithubRepository
import com.monty.jetgooglerepository.android.presentation.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .hostnameVerifier { _, _ -> true }
            .build()

        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesDatabase(
        app: Application
    ): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "googleRepoDB.db")
            .build()
    }

    @Singleton
    @Provides
    fun providesAppDao(
        appDatabase: AppDatabase
    ): AppDao {
        return appDatabase.getAppDao()
    }

    @Singleton
    @Provides
    fun providesLocalDataSource(
        dao: AppDao
    ): LocalDataSource {
        return LocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun providesGithubRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): GithubRepository {
        return GithubRepositoryImpl(
            localDataSource, remoteDataSource
        )
    }
}




















