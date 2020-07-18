package com.anangkur.budgetku.data.model.budget

data class CategoryEntity(
    val title: String,
    val image: String,
    val child: List<CategoryEntity> = emptyList()
)