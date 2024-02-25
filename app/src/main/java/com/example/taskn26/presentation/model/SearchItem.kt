package com.example.taskn26.presentation.model

import com.example.taskn26.domain.model.GetSearchItem

data class SearchItem(
    val id: String,
    val name: String,
    val nameDe: String,
    val createdAt: String,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?,
    val main: String?,
    val children: List<GetSearchItem>,
    val numberOfChildren: Int
)