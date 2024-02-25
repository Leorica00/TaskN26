package com.example.taskn26.data.mapper

import com.example.taskn26.data.model.SearchItemDto
import com.example.taskn26.domain.model.GetSearchItem

fun SearchItemDto.toDomain(): GetSearchItem = GetSearchItem(
    id = id,
    name = name,
    nameDe = nameDe,
    createdAt = createdAt,
    bglNumber = bglNumber,
    bglVariant = bglVariant,
    orderId = orderId,
    main = main,
    children = children.map { it.toDomain() }
)