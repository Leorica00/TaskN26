package com.example.taskn26.presentation.state

import com.example.taskn26.presentation.model.SearchItem

data class SearchItemState(
    val filteredItems: List<SearchItem>? = null,
    val isLoading: Boolean = false,
    val errorMessage: Int? = null
)
