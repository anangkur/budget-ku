package com.anangkur.budgetku.domain.model.budget

data class Category(
    val title: String,
    val image: String,
    val child: List<Category> = emptyList()
)