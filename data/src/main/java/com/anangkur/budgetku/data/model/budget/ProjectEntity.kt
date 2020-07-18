package com.anangkur.budgetku.data.model.budget

data class ProjectEntity(
    val id: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val listCategory: List<CategoryProjectEntity>
)