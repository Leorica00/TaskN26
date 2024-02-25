package com.example.taskn26.presentation.event

sealed interface SearchEvent {
    class GetSearchedItemsEvent(val filter: String): SearchEvent
}