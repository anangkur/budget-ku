package com.anangkur.budgetku.presentation.model.budget

data class CategoryProjectView (
    val id: String,
    val title: String,
    val image: String,
    val value: Double,
    val spend: Double
)