package com.example.taskn26.domain.usecase

import com.example.taskn21.data.remote.common.Resource
import com.example.taskn26.domain.model.GetSearchItem
import com.example.taskn26.domain.repository.SearchItemsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFilterItemsUseCase @Inject constructor(private val searchItemsRepository: SearchItemsRepository) {

    suspend operator fun invoke(filterName: String) = withContext(IO) {
        return@withContext flow<Resource<List<GetSearchItem>>> {
            searchItemsRepository.getSearchItems().collect {
                when(it) {
                    is Resource.Success -> emit(Resource.Success(response = filterData(it.response, filterName)))
                    is Resource.Loading -> emit(Resource.Loading(loading = it.loading))
                    is Resource.Error -> emit(Resource.Error(error = it.error, throwable = it.throwable))
                }
            }
        }

    }

    private fun filterData(items: List<GetSearchItem>, searchTerm: String): MutableList<GetSearchItem> {
        val filteredItems = mutableListOf<GetSearchItem>()
        for (item in items) {
            if (item.name.contains(searchTerm, ignoreCase = true)) {
                filteredItems.add(item.copy(numberOfChildren = assignDepth(item)))
            }
            filteredItems.addAll(filterData(item.children, searchTerm))
        }
        return filteredItems
    }

    private fun assignDepth(searchItem: GetSearchItem): Int {
        return if (searchItem.children.isEmpty()) {
            0
        } else {
            val childDepths = searchItem.children.map { assignDepth(it) }
            (childDepths.maxOrNull() ?: 0) + 1
        }
    }
}