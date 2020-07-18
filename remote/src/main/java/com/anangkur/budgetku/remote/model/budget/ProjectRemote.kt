package com.anangkur.budgetku.remote.model.budget

data class ProjectRemote(
    val title: String,
    val startDate: String,
    val endDate: String,
    val listCategory: List<CategoryProjectRemote>
)