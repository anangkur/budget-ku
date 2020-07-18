package com.anangkur.budgetku.presentation.model.dashboard

import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView

data class ProjectView(
    val id: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val listCategory: List<CategoryProjectView>
)