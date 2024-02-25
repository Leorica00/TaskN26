package com.example.taskn26.domain.repository

import com.example.taskn21.data.remote.common.Resource
import com.example.taskn26.domain.model.GetSearchItem
import kotlinx.coroutines.flow.Flow

interface SearchItemsRepository {
    suspend fun getSearchItems(): Flow<Resource<List<GetSearchItem>>>
}