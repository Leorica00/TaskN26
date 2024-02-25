package com.example.taskn26.presentation.mapper

import com.example.taskn26.domain.model.GetSearchItem
import com.example.taskn26.presentation.model.SearchItem

fun GetSearchItem.toPresentation() = SearchItem(
    id = id,
    name = name,
    nameDe = nameDe,
    createdAt = createdAt,
    bglNumber = bglNumber,
    bglVariant = bglVariant,
    orderId = orderId,
    main = main,
    children = children.map { it },
    numberOfChildren = numberOfChildren
)
