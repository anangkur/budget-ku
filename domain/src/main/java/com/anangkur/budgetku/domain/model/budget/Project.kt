package com.anangkur.budgetku.domain.model.budget

data class Project(
    val id: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val listCategory: List<CategoryProject>
)