package com.example.taskn26.data.repository

import com.example.taskn21.data.remote.common.HandleResponse
import com.example.taskn21.data.remote.common.Resource
import com.example.taskn26.data.mapper.base.asResource
import com.example.taskn26.data.mapper.toDomain
import com.example.taskn26.data.service.SearchApiService
import com.example.taskn26.domain.model.GetSearchItem
import com.example.taskn26.domain.repository.SearchItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchItemsRepositoryImpl @Inject constructor(private val apiService: SearchApiService, private val handleResponse: HandleResponse): SearchItemsRepository {
    override suspend fun getSearchItems(): Flow<Resource<List<GetSearchItem>>> {
        return handleResponse.safeApiCall {
            apiService.getSearchItems()
        }.asResource {
            it.map { it.toDomain() }
        }
    }
}