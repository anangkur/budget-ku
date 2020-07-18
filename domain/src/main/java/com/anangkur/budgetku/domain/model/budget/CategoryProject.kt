package com.anangkur.budgetku.domain.model.budget

data class CategoryProject(
    val id: String,
    val title: String,
    val image: String,
    val value: Double,
    val spend: Double
)