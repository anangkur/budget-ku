package com.anangkur.budgetku.presentation.model.budget

data class CategoryView(
    val title: String,
    val image: String,
    val child: List<CategoryView> = emptyList()
)