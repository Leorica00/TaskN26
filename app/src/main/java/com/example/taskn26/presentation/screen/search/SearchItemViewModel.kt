package com.example.taskn26.presentation.screen.search

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskn21.data.remote.common.Resource
import com.example.taskn26.domain.usecase.GetFilterItemsUseCase
import com.example.taskn26.presentation.event.SearchEvent
import com.example.taskn26.presentation.state.SearchItemState
import com.example.taskn26.presentation.mapper.toPresentation
import com.example.taskn26.presentation.util.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemViewModel @Inject constructor(private val searchItemsUseCase: GetFilterItemsUseCase): ViewModel() {
    private val _searchItemsStateFlow = MutableStateFlow(SearchItemState())
    val searchItemsStateFlow = _searchItemsStateFlow.asStateFlow()

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.GetSearchedItemsEvent -> getSearchedItems(filter = event.filter)
        }
    }

    private fun getSearchedItems(filter: String) {
        viewModelScope.launch {
            searchItemsUseCase(filter).collect {resource ->
                d("resource123", resource.toString())
                when(resource) {
                    is Resource.Success -> _searchItemsStateFlow.update { currentState -> currentState.copy(filteredItems = resource.response.map { it.toPresentation() }) }
                    is Resource.Loading -> _searchItemsStateFlow.update { currentState -> currentState.copy(isLoading = resource.loading) }
                    is Resource.Error -> _searchItemsStateFlow.update { currentState -> currentState.copy(errorMessage = getErrorMessage(resource.error)) }
                }
            }
        }
    }
}