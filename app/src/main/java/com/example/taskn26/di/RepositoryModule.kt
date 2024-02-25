package com.example.taskn26.di

import com.example.taskn21.data.remote.common.HandleResponse
import com.example.taskn26.data.repository.SearchItemsRepositoryImpl
import com.example.taskn26.data.service.SearchApiService
import com.example.taskn26.domain.repository.SearchItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteClothesRepository(searchApiService: SearchApiService, handleResponse: HandleResponse): SearchItemsRepository =
        SearchItemsRepositoryImpl(
            apiService = searchApiService,
            handleResponse = handleResponse,
        )
}